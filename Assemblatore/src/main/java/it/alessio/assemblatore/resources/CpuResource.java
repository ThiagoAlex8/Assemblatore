/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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


@Path("cpu")
public class CpuResource {
    private static AmazonDynamoDBClient client;
    
    @Context
    private UriInfo context;
    public CpuResource()
    {
    }
    public CpuResource(AmazonDynamoDBClient client)
    {
        this.client = client;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpu(@PathParam("id") String id){
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
        try
        {
            cpu = db_mapper.load(CpuMapper.class, id, null);
            return Response.status(200).entity(cpu).build();
        }
        catch (AmazonServiceException ase) 
        {
            System.out.println(ase.getErrorMessage());
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }    
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpu(@PathParam("id") int id, String content) throws IOException
    {
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
        try
        {
            cpu = obj_mapper.readValue(content, CpuMapper.class);
            db_mapper.save(cpu);
            //UriBuilder builder = context.getAbsolutePathBuilder()
            return Response.status(200).entity("Cpu (id: " + id + ") updated!!! ").build(); 
        }
        catch(AmazonServiceException ase)
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }     
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCpu(@PathParam("id") String id, String content) throws IOException
    {         
        UUID uuid = UUID.randomUUID();
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
        
        try
        {
            cpu = obj_mapper.readValue(content, CpuMapper.class);
            cpu.setUuid(uuid.toString());
            db_mapper.save(cpu);
            UriBuilder builder = context.getAbsolutePathBuilder();
            builder.path(uuid.toString());
            return Response.created(builder.build()).build();
            
        }
        catch(AmazonServiceException ase)
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
        //ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
        try 
        {   
            cpu = db_mapper.load(CpuMapper.class,id);
            db_mapper.delete(cpu);
            return Response.status(200).entity("Cpu (id: " + id +") deleted!!!").build();
        } catch (Exception ese) {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
}
