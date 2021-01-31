package project.node;

import project.beans.NodeDescriptor;
import project.beans.NodeList;
import project.node.TokenServiceGrpc.*;
import project.node.Token.TokenObject.*;
import project.node.Token.*;
import io.grpc.stub.StreamObserver;
import project.sensor.Measurement;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

public class TokenServiceImpl extends TokenServiceImplBase {

    @Override
    public void sendToken(TokenObject request, StreamObserver<ACK> responseObserver){

        // Getting token data
        List<AverageMeasure> averages = request.getAverageList();
        ArrayList<AverageMeasure> averageMeasures = new ArrayList<AverageMeasure>(averages) ;

        // Elaborate token
        ArrayList<Measurement> toMeasurement = toMeasurement(averageMeasures);
        TokenJava.getInstance().setAverageMeasures(toMeasurement);
        TokenJava.getInstance().awakening();

        // Building the AddResponse message
        ACK response = ACK.newBuilder().setOk("Token successfully delivered").build();

        // Sending the Ack back
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void insertNotification(NodeDescription request, StreamObserver<ACK> responseObserver){
        String id = request.getId();
        String ip = request.getIp();
        String port = request.getPort();
        NodeDescriptor newNode = new NodeDescriptor(id, ip, port);

        NodeList.getInstance().add(newNode);

        // Building the AddResponse message
        ACK response = ACK.newBuilder().setOk("Inner node successfully insert").build();

        // Sending the Ack back
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteNotification(NodeDescription request, StreamObserver<ACK> responseObserver){
        NodeList.getInstance().delete(Integer.parseInt(request.getId()));

        // Building the AddResponse message
        ACK response = ACK.newBuilder().setOk("Inner node successfully deleted").build();

        // Sending the Ack back
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ArrayList<Measurement> toMeasurement(ArrayList<AverageMeasure> averageMeasures){
        ArrayList<Measurement> result = new ArrayList<Measurement>();
        String id = "id";
        String type = "type";
        for(int i=0; i<averageMeasures.size(); i++)
            result.add(new Measurement(id, type, averageMeasures.get(i).getValue(), (long)averageMeasures.get(i).getTimestamp()));
        return result;
    }

}