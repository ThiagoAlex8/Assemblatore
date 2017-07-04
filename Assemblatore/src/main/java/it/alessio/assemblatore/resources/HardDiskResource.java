/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.alessio.assemblatore.service.Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author aless
 */
@Path("hd")
public class HardDiskResource {
    private static AmazonDynamoDBClient client;
    
    
    @Context
    private UriInfo context;
    public HardDiskResource()
    {
    }
    
    public HardDiskResource(AmazonDynamoDBClient client)
    {
        this.client = client;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHD(@PathParam("id") int id) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String resourceString;
        try {
            resourceString = mapper.writeValueAsString(Service.getQuantitaHD(id));
            System.out.println("GET /hd/"+id);
            //Service.getQuantitaHD(Integer.valueOf(id));
            //return Response.status(200).entity("{\"Status\":\"Hard disk (id: " + id +") Success!!!\"}").build();
        } catch (JsonProcessingException ex) {
            return Response.status(500).entity(new ObjectMapper().writeValueAsBytes(ex.getMessage())).build();
        }
        return Response.status(200).entity(resourceString).build();
        
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHD(@PathParam("id") int id, String content) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        HardDisk hd = mapper.readValue(content, HardDisk.class);
        Service.setQuantitaHD(id, hd);
        System.out.println("PUT /hd/"+id);
        return Response.status(200).entity("Hard disk (id: " + id + ") updated!!!").build();
        
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response postCpu(@PathParam("id") int id, String content) throws IOException
            
    {   ObjectMapper mapper = new ObjectMapper();
        HardDisk hd = mapper.readValue(content, HardDisk.class);
        Service.setQuantitaHD(id, hd);
        //System.out.println("POST /hd/"+id);
       // System.out.println("POST /cpu/"+id);
        return Response.created(context.getAbsolutePath()).build();
    }
    
     @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Hard disk (id: " + id +" deleted!!!").build();
    }
    
}
