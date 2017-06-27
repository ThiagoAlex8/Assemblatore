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
@Path("hd")
public class HardDiskResource {
    public HardDiskResource()
    {
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHD(@PathParam("id") String id){
        System.out.println("GET /hd/"+id);
        Service.getQuantitaHD(Integer.valueOf(id));
        return Response.status(200).entity("{\"Status\":\"Hard disk (id: " + id +") Success!!!\"}").build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHD(@PathParam("id") String id, String content){
        System.out.println("PUT /hd/"+id);
        return Response.status(200).entity("Hard disk (id: " + id + ") updated!!!").build();
        
    }
    
    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response postCpu(@PathParam("id") String id)
            
    {   
        System.out.println("POST /hd/"+id);
        Service.setQuantitaHD(Integer.valueOf(id),new HardDisk(id, 300));
       // System.out.println("POST /cpu/"+id);
        return Response.status(200).entity("Hard disk (id: " + id + ") created!!!").build();
    }
    
     @DELETE
    @Path("{id}")
    @Produces (MediaType.APPLICATION_JSON)
    
    public Response deleteCpu(@PathParam("id") String id)
    {
        return Response.status(200).entity("Hard disk (id: " + id +" deleted!!!").build();
    }
    
}
