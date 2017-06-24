/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.service;

/**
 *
 * @author alessio
 */


import com.sun.net.httpserver.HttpServer;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import it.alessio.assemblatore.resources.Ping;

public class Service {
    
    
    public static void main(String[] args)
    {
        
        System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
            
       
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8080).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new Ping());
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
}
}
