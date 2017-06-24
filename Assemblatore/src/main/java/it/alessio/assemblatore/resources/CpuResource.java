/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aless
 */
@Path("cpu")
public class CpuResource {
    
    public CpuResource()
    {
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCpu(@PathParam("id") String id){
        System.out.println("GET /cpu/"+id);
        return Response.status(200).entity("{\"Status\":\"Success!!!\"}").build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCpu(@PathParam("id") String id, String content){
        System.out.println("PUT /cpu/"+id);
        return Response.status(200).entity("Cpu updated!!!").build();
        
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response postCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Cpu created!!!").build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Cpu deleted!!!").build();
    }
    
}
