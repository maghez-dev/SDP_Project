package project.node;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.beans.NodeDescriptor;
import project.beans.NodeList;
import project.sensor.PM10Simulator;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NodeService {
    public static String GATEWAY_URL = "http://localhost:8080/Project2/gateway";

    public static void main(String args[]) {
        try {

            /////////////////////////////////////// NODE INITIALIZATION ///////////////////////////////////////////////

            String id = args[0];
            String port = args[1];

            System.out.println("Node init...");
            Server server = ServerBuilder.forPort(Integer.parseInt(port)).addService(new TokenServiceImpl()).build();
            server.start();

            System.out.println("Node ID: " + args[0] + "\nNode PORT:" + args[1]);
            NodeDescriptor node = new NodeDescriptor();
            node.setId(id);
            node.setIp(GATEWAY_URL);
            node.setPort(port);

            ///////////////////////////////////// STARTING PM10 SIMULATOR /////////////////////////////////////////////

            NodeBuffer myBuff = NodeBuffer.getInstance();
            PM10Simulator p10sim = new PM10Simulator(myBuff);
            p10sim.start();

            ///////////////////////////////////////// SETUP WITH GATEWAY //////////////////////////////////////////////

            Client client = ClientBuilder.newClient();
            WebTarget networkAdd = client.target(GATEWAY_URL).path("/network/add");

            Invocation.Builder invocationBuilder = networkAdd.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(node, MediaType.APPLICATION_JSON));

            if(response.getStatus() != 200) {
                System.out.println("ERROR: Status" + response.getStatus());
                throw new Exception();
            }
            System.out.println("Node successfully part of the network");
            NodeList list = response.readEntity((NodeList.class));
            ArrayList<NodeDescriptor> nodeList = list.getNodeList();

            ///////////////////////////////////////// NETWORK AGGREGATION /////////////////////////////////////////////
            NodeNetworkThread netThread = new NodeNetworkThread(id, port, GATEWAY_URL, nodeList);
            netThread.start();

            //////////////////////////////////////// PROCESS TERMINATION //////////////////////////////////////////////
            while(true){
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                String command = inFromUser.readLine();
                if(command.equals("quit")){
                    break;
                }
            }
            p10sim.stopMeGently();
            netThread.stopMeGently();
            p10sim.join();
            netThread.join();

            WebTarget networkDelete = client.target(GATEWAY_URL).path("/network/delete").path(id);
            invocationBuilder =  networkDelete.request(MediaType.APPLICATION_JSON);
            response = invocationBuilder.delete();

            if(response.getStatus() != 200) {
                System.out.println("Server error, status: " + response.getStatus());
            } else {
                System.out.println(response.readEntity(String.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
