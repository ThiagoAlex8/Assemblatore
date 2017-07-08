/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.alessio.assemblatore.service.Service;
import java.io.IOException;
import java.util.UUID;
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
import javax.ws.rs.core.UriBuilder;
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
    public Response getHD(@PathParam("id") String id){
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        HardDiskMapper hd;
        try {
            hd = db_mapper.load(HardDiskMapper.class,id,null);
            return Response.status(200).entity(hd).build();
        } catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"Resource not found").build();
        }
        finally
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHD(@PathParam("id") int id, String content) throws IOException{
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        HardDiskMapper hd;
        try{
            hd = obj_mapper.readValue(content, HardDiskMapper.class);
            db_mapper.save(hd);
            return Response.status(200).entity("Hard disk updated!!!").build();
        }
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"Resource not found").build();
        }
        finally
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
        
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCpu(@PathParam("id") int id, String content) throws IOException    
    {   
        UUID uuid = UUID.randomUUID();
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        HardDiskMapper hd;
        try
        {
            hd = obj_mapper.readValue(content, HardDiskMapper.class);
            hd.setUuid(uuid.toString());
            db_mapper.save(hd);
            UriBuilder builder = context.getAbsolutePathBuilder();
            builder.path(uuid.toString());
            return Response.created(builder.build()).build();
        }
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"Resource not found").build();
        }
        finally
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
     @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id) throws IOException
    {
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        HardDiskMapper hd;
        try
        {
            hd = db_mapper.load(HardDiskMapper.class,id);
            db_mapper.delete(hd);
            return Response.status(204).entity("Cpu (id: " + id +") deleted!!!").build();
        }
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"Resource not found").build();
        }
        finally
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
}
