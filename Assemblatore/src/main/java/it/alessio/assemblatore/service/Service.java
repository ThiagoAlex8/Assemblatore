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
import it.alessio.assemblatore.resources.Cpu;
import it.alessio.assemblatore.resources.CpuResource;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import it.alessio.assemblatore.resources.Ping;
import java.util.ArrayList;

public class Service {
    
    private static ArrayList<Cpu> quantita;

    public static Cpu getQuantita(int pos) {
        return quantita.get(pos);
    }

    public static void setQuantita(int pos, Cpu cpu) {
        quantita.add(0, cpu);
    }
    
    
    
    public static void main(String[] args)
    {
        quantita = new ArrayList<Cpu>();
        System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
            
       
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8081).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new Ping());
        config.register(new CpuResource());
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
}
}
