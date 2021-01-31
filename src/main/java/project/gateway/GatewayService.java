package project.gateway;

import project.beans.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gateway")
public class GatewayService {

    /////////////////////////// NETWORK MANAGEMENT ///////////////////////////////////////////

    // Adding a node to the list, returning the list on node actually part of the network
    @Path("network/add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addNode(NodeDescriptor nodeDescriptor){
        List<NodeDescriptor> netNodeDescriptors = NodeList.getInstance().add(nodeDescriptor);
        System.out.println("New node added, current list:\n" + NodeList.getInstance().getNodeList());
        if(netNodeDescriptors != null)
            return Response
                    .status(Response.Status.OK)
                    .entity(NodeList.getInstance())
                    .build();
        else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    // Delete a node, removing it from the node list
    @Path("network/delete/{id}")
    @DELETE
    public Response deleteNode(@PathParam("id") int id){
        NodeList.getInstance().delete(id);
        System.out.println("Node deleted, current list:\n" + NodeList.getInstance().getNodeList());
        return Response
                .status(Response.Status.OK)
                .entity("Node deleted successfully from gateway!")
                .build();
    }

    /////////////////////////// DATABASE UPDATES ///////////////////////////////////////////

    // Adding data to the database
    @Path("database/update")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addStat(MeasurementWrapper wrapper){
        Database.getInstance().add(wrapper);
        return Response.status(Response.Status.OK).build();
    }

    /////////////////////////// CLIENT INTERFACE ///////////////////////////////////////////

    // Get nodes number
    @Path("client/nodes")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getNodes(){
        return Response.ok(NodeList.getInstance().getNodeList().size()).build();
    }

    // Last n stats (with timestamp) from the quarter
    @Path("client/measures/{num}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastMeasures(@PathParam("num") String num){
        int n = Integer.parseInt(num);
        Dataset lastMeasurement = Database.getInstance().getLastStats(n);
        return Response.ok(lastMeasurement).build();
    }

    // Get standard deviation of last n stats from the quarter
    @Path("client/deviation/{num}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastDeviation(@PathParam("num") String num){
        int n = Integer.parseInt(num);
        return Response.ok(Database.getInstance().getDeviation(n)).build();
    }

    // Get average of last n stats from the quarter
    @Path("client/average/{num}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastAverage(@PathParam("num") String num){
        int n = Integer.parseInt(num);
        return Response.ok(Database.getInstance().getAverage(n)).build();
    }
}
