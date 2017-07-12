/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.alessio.assemblatore.service.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.ws.rs.QueryParam;
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
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"403, \n \"Message\":\"Resource not found").build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCpu(@QueryParam("brand") String brand, @QueryParam("id") String id)
    {
        
        //searchInDynamo(brand);
        return Response.status(200).entity("{\"Status\":\"Ok\"}").build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpu(@PathParam("id") int id, String content) throws IOException
    {
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
        UriBuilder builder = context.getAbsolutePathBuilder();
        try
        {
            cpu = obj_mapper.readValue(content, CpuMapper.class);
            db_mapper.save(cpu);
            //UriBuilder builder = context.getAbsolutePathBuilder()
            return Response.status(201).entity("{\"Status\":\"201, \n \"Message\":\"Resource updated}").build();
        }
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"403, \n \"Message\":\"Resource not found}").build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCpu(@PathParam("id") String id, String content)
    {         
        UUID uuid = UUID.randomUUID();
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        ObjectMapper obj_mapper = new ObjectMapper();
        CpuMapper cpu;
       // UriBuilder builder = context.getAbsolutePathBuilder();
        
        try
        {
            cpu = obj_mapper.readValue(content, CpuMapper.class);
            cpu.setUuid(uuid.toString());
            db_mapper.save(cpu);
            //builder.path(uuid.toString());
            return Response.status(201).entity("{\"Status\":\"201, \n \"Message\":\"Resource created}").build();
            
        }
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"403, \n \"Message\":\"Resource not found}").build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("{\"Status\":\"500, \n \"Message\":\"" + ex.getMessage()+"\"}").build();
        }
    }
    
    @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
     public Response deleteCpu(@PathParam("id") String id) throws IOException
    {
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        CpuMapper cpu;
        try 
        {   
            cpu = db_mapper.load(CpuMapper.class,id);
            db_mapper.delete(cpu);
            return Response.status(204).entity("{\"Status\":\"204, \n \"Message\":\"Resource deleted}").build();
        } 
        catch (ResourceNotFoundException ex) 
        {
            return Response.status(404).entity("{\"Status\":\"403, \n \"Message\":\"Resource not found}").build();
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
     
     /*protected void searchInDynamo(String brand)
    {
        try
        {
            
        
        DynamoDB db_mapper = new DynamoDB(client);
            QuerySpec spec = new QuerySpec().withKeyConditionExpression("BrandCpu = :brandvalue")
                    .withValueMap(new ValueMap().withString(":brandvalue", "intel"));
            ItemCollection<QueryOutcome> items = index.query(spec);  
        List<CpuMapper> lista = db_mapper.query(CpuMapper.class, queryExpression);
        for(CpuMapper cpu : lista)
        {
            System.out.println(cpu.getIdCpu());
            System.out.println(cpu.getBrandCpu());
            System.out.println(cpu.getPrice());
            
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    */
}
