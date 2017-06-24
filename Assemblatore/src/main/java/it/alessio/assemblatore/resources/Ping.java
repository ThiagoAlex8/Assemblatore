/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.resources;

/**
 *
 * @author aless
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author maverick
 */
@Path("/ping")
public class Ping {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
            System.out.println("GET /ping");
            return Response.status(Response.Status.OK).build();
        }
}
