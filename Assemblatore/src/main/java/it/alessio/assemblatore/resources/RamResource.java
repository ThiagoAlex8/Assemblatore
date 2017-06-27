/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

import it.alessio.assemblatore.service.Service;
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
@Path("ram")
public class RamResource {
    public RamResource()
    {
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRam(@PathParam("id") String id){
        System.out.println("GET /ram/"+id);
        Service.getQuantitaRam(Integer.valueOf(id));
        return Response.status(200).entity("{\"Status\":\"Ram Success!!!\"}").build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRam(@PathParam("id") String id, String content){
        System.out.println("PUT /ram/"+id);
        return Response.status(200).entity("RAM updated!!!").build();
        
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response postCpu(@PathParam("id") String id)
            
    {   
        System.out.println("POST /ram/"+id);
        Service.setQuantitaRam(Integer.valueOf(id),new Ram(id, 300));
       // System.out.println("POST /cpu/"+id);
        return Response.status(200).entity("Ram created!!!").build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Ram deleted!!!").build();
    }
}
