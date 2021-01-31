package project.node;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import project.beans.MeasurementWrapper;
import project.node.TokenServiceGrpc.*;
import project.node.Token.*;
import project.beans.NodeDescriptor;
import project.beans.NodeList;
import project.sensor.Measurement;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class NodeNetworkThread extends Thread {
    protected volatile boolean stopCondition = false;
    private String id;
    private String port;
    private String gatewayUrl;
    private String successorNodeUrl;

    public NodeNetworkThread(String id, String port, String gatewayUrl, ArrayList<NodeDescriptor> nodeList) {
        this.id = id;
        this.port = port;
        this.gatewayUrl = gatewayUrl;
        for(NodeDescriptor nd : nodeList){
            NodeList.getInstance().add(nd);
        }
        updateForwardNodeUrl();
    }

    @Override
    public void run() {
        // Say to each node that i'm in
        insertNotification();

        while(!stopCondition){
            try {
                if(NodeList.getInstance().size() > 1) {
                    try {
                        System.out.println("I'm going to sleep");
                        System.out.println("---------------------------------------------------------------------");
                        if(stopCondition) break;
                        TokenJava.getInstance().sleeping();
                        if(stopCondition) break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("I have token");
                sleep(5000);

                // Check for a new successor, the network may have changed
                updateForwardNodeUrl();

                // Updates token and sends data average to gateway if needed
                tokenUpdate();

                // Send token Forward
                sendToken();

                System.out.println("Number of nodes: " + NodeList.getInstance().size());
                System.out.println("Node list: " + NodeList.getInstance().getNodeList());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Say to each node that i'm out
        deleteNotification();

        System.out.println("Node shutting down...");
    }

    public void stopMeGently() {
        stopCondition = true;
        TokenJava.getInstance().awakening();
    }

    //////////////////////////////////////////// NETWORK UPDATES //////////////////////////////////////////////////////

    // Sends a message to all nodes to tell them that this node has joined the network
    private void insertNotification(){
        String nodeUrl = null;
        ManagedChannel channel = null;
        TokenServiceBlockingStub stub = null;
        NodeDescription request = null;
        ACK response = null;

        for(int i=0; i<NodeList.getInstance().size(); i++) {
            nodeUrl = "localhost:" + NodeList.getInstance().getNodeList().get(i).getPort();
            channel = ManagedChannelBuilder.forTarget(nodeUrl).usePlaintext().build();
            stub = TokenServiceGrpc.newBlockingStub(channel);

            request = NodeDescription.newBuilder()
                    .setId(this.id)
                    .setIp(this.gatewayUrl)
                    .setPort(this.port)
                    .build();
            response = stub.insertNotification(request);
        }
        System.out.println(response.getOk());
        channel.shutdown();
    }

    // Sends a message to all nodes to tell them that this node has left the network
    private void deleteNotification(){
        String nodeUrl = null;
        ManagedChannel channel = null;
        TokenServiceBlockingStub stub = null;
        NodeDescription request = null;
        ACK response = null;

        for(int i=0; i<NodeList.getInstance().size(); i++) {
            if(!NodeList.getInstance().getNodeList().get(i).getPort().equals(this.port)) {
                nodeUrl = "localhost:" + NodeList.getInstance().getNodeList().get(i).getPort();
                channel = ManagedChannelBuilder.forTarget(nodeUrl).usePlaintext().build();
                stub = TokenServiceGrpc.newBlockingStub(channel);

                request = NodeDescription.newBuilder()
                        .setId(this.id)
                        .setIp(this.gatewayUrl)
                        .setPort(this.port)
                        .build();
                response = stub.deleteNotification(request);
                System.out.println(response.getOk());
            }
        }
        if(channel != null)
            channel.shutdown();
    }

    // Updates successor node, to keep a consistent ring network
    private void updateForwardNodeUrl(){
        this.successorNodeUrl = NodeList.getInstance().getSuccessor(id);
    }

    /////////////////////////////////////////// GATEWAY COMMUNICATION /////////////////////////////////////////////////

    // Use of Jersey client to send data to Gateway server
    private void tokenUpdate() throws Exception {
        Measurement nodeAverage = NodeBuffer.getInstance().localAverage();
        TokenJava.getInstance().addAverageMeasures(nodeAverage);

        // Computing global average and sending it to gateway only if token is full
        if(TokenJava.getInstance().size() >= NodeList.getInstance().size()) {
            Measurement globalMeasurement = computeGlobalMeasurement();
            MeasurementWrapper wrapper = new MeasurementWrapper(
                    globalMeasurement.getId(),
                    globalMeasurement.getType(),
                    globalMeasurement.getValue(),
                    globalMeasurement.getTimestamp());

            Client client = ClientBuilder.newClient();
            WebTarget databaseUpdate = client.target(gatewayUrl).path("/database/update");

            Invocation.Builder invocationBuilder = databaseUpdate.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(wrapper, MediaType.APPLICATION_JSON));

            if(response.getStatus() != 200) {
                System.out.println("ERROR: Status" + response.getStatus());
                throw new Exception();
            }
            TokenJava.getInstance().clear();
        }
    }

    private Measurement computeGlobalMeasurement(){
        ArrayList<Measurement> data = TokenJava.getInstance().getAverageMeasures();
        String id = "id";
        String type = "type";
        double value = 0;
        long timestamp = 0;
        Measurement globalAverage = new Measurement(id, type, value, timestamp);

        for(int i=0; i < data.size(); i++) {
            Measurement tmp = data.get(i);
            value += tmp.getValue();
            timestamp += tmp.getTimestamp();
            if (i == data.size()/2) {
                globalAverage.setId(tmp.getId());
                globalAverage.setType(tmp.getType());
            }
        }
        globalAverage.setValue(value/data.size());
        globalAverage.setTimestamp(timestamp/data.size());

        return globalAverage;
    }

    ////////////////////////////////////////////// TOKEN SENDING //////////////////////////////////////////////////////

    private void sendToken() {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(successorNodeUrl).usePlaintext().build();
        TokenServiceBlockingStub stub = TokenServiceGrpc.newBlockingStub(channel);
        ArrayList<TokenObject.AverageMeasure> toAverage = toAverageMeasure(NodeBuffer.getInstance().getData());

        TokenObject request = TokenObject.newBuilder()
                .addAllAverage(toAverage)
                .build();
        ACK response = stub.sendToken(request);
        System.out.println(response.getOk());
        channel.shutdown();
    }

    private ArrayList<TokenObject.AverageMeasure> toAverageMeasure(ArrayList<Measurement> data) {
        ArrayList<TokenObject.AverageMeasure> averageMeasures = new ArrayList<Token.TokenObject.AverageMeasure>();
        for (Measurement measurement : data) {
            TokenObject.AverageMeasure averageMeasure = TokenObject.AverageMeasure.newBuilder()
                    .setValue(measurement.getValue())
                    .setTimestamp(measurement.getTimestamp())
                    .build();
            averageMeasures.add(averageMeasure);
        }
        return averageMeasures;
    }

}
