/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
    public Response getCpu(@PathParam("id") int id) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String resourceString;
        try {
            
            resourceString = mapper.writeValueAsString(Service.getQuantita(id));
            
            System.out.println("GET /cpu/"+id);
            //Service.getQuantita(Integer.valueOf(id));
            //return Response.status(200).entity("{\"Status\":\"Cpu (id: " + id +") Success!!!\"}").build();
            
            
        } catch (JsonProcessingException ex) {
            return Response.status(500).entity(new ObjectMapper().writeValueAsBytes(ex.getMessage())).build();
        }
        return Response.status(200).entity(resourceString).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpu(@PathParam("id") int id, String content) throws IOException
    {
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu = obj_mapper.readValue(content, CpuMapper.class);
        //cpu.setIdCpu(id);
        db_mapper.save(cpu);
        System.out.println("PUT /cpu/"+id);
        return Response.status(200).entity("Cpu (id: " + id + ") updated!!! ").build();
        
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCpu(@PathParam("id") int id, String content) throws IOException
            {   
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu = obj_mapper.readValue(content, CpuMapper.class);
        db_mapper.save(cpu);
        
        System.out.println(client.listTables().toString());
        //Service.setQuantita(Integer.valueOf(id),new Cpu(id, 300));
       // System.out.println("POST /cpu/"+id);
        return Response.created(context.getAbsolutePath()).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Cpu (id: " + id +") deleted!!!").build();
    }
    
}
