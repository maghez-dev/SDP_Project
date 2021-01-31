package project.client;

import project.beans.Dataset;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AnalystClient {
    public static String GATEWAY_URL = "http://localhost:8080/Project2/gateway";

    public static void main(String[] args) {
        boolean alive = true;

        while(alive) {
            System.out.println("\n---------------------------------------------------------------------\n");
            System.out.println("New operation (type 'help' for a list of commands):");

            // Get input from console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<String> command = new ArrayList<String>();
            try {
                String rawInput = reader.readLine();
                String[] cookedInput = rawInput.split(" ");
                command.addAll(Arrays.asList(cookedInput));
                if(command.size() == 2) Integer.parseInt(command.get(1));
                if(command.size() > 2) throw new IOException();
            } catch (Exception e) {
                System.out.println("Malformed input");
                command.clear();
                command.add("");
            }

            Client client = ClientBuilder.newClient();

            if(command.get(0).equals("help") && command.size() == 1) {
                System.out.println("- Get current node number: nodes.");
                System.out.println("- Get last n measurements: measures n.");
                System.out.println("- Standard deviation of last n measurement: deviation n.");
                System.out.println("- Average of last n measurement: average n.");
            }
            else if(command.get(0).equals("quit") && command.size() == 1) {
                alive = false;
            }
            else if(command.get(0).equals("nodes") && command.size() == 1) {

                WebTarget networkAdd = client.target(GATEWAY_URL).path("/client/nodes");

                Invocation.Builder invocationBuilder = networkAdd.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();

                if(response.getStatus() != 200) {
                    System.out.println("Task failed: Status" + response.getStatus());
                }
                else {
                    Integer nodeNum = response.readEntity((Integer.class));
                    System.out.println("Network nodes: " + nodeNum);
                }
            }
            else if(command.get(0).equals("measures") && command.size() == 2) {
                WebTarget networkAdd = client.target(GATEWAY_URL).path("/client/measures/").path(command.get(1));
                Invocation.Builder invocationBuilder = networkAdd.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();

                if(response.getStatus() != 200) {
                    System.out.println("Task failed: Status" + response.getStatus());
                }
                else {
                    Dataset data = response.readEntity((Dataset.class));
                    System.out.println("Last " +command.get(1) + " measures");
                    for(int i=0; i< data.size(); i++) {
                        System.out.println((i+1)+") Value: " + data.getValue(i) + " Timestamp: " + data.getTimestamp(i));
                    }
                    //System.out.println("Value: " + data.getValues() + "\nTimestamp: " + data.getTimestamps());
                }
            }
            else if(command.get(0).equals("deviation") && command.size() == 2){
                WebTarget networkAdd = client.target(GATEWAY_URL).path("/client/deviation/").path(command.get(1));
                Invocation.Builder invocationBuilder = networkAdd.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();

                if(response.getStatus() != 200) {
                    System.out.println("Task failed: Status" + response.getStatus());
                }
                else {
                    Double data = response.readEntity((Double.class));
                    System.out.println("Standard deviation of last " + command.get(1) + " measurements:\n" + data);
                }
            }
            else if(command.get(0).equals("average") && command.size() == 2) {
                WebTarget networkAdd = client.target(GATEWAY_URL).path("/client/average/").path(command.get(1));
                Invocation.Builder invocationBuilder = networkAdd.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();

                if(response.getStatus() != 200) {
                    System.out.println("Task failed: Status" + response.getStatus());
                }
                else {
                    double data = response.readEntity((Double.class));
                    System.out.println("Average of last " + command.get(1) + " measurements:\n" + data);
                }
            }
            else {
                System.out.println("Unrecognized input");
            }
        }
        System.out.println("Client shutting down... bye!");
    }
}
