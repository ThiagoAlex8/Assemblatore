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
import it.alessio.assemblatore.resources.Ram;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.core.Response;

public class Service {
    
    //private static ArrayList<Cpu> quantita;
    private static Cpu quantita[];
    private static Ram size[];
    
    public static String getQuantita(int pos) {
        //return quantita.get(pos);
        System.out.println("OK!!!");
        return Arrays.toString(quantita);
    }

    public static void setQuantita(int pos, Cpu cpu) {
        System.out.println("Cpu creata!!!");
        quantita[pos] = cpu;
    }
    public static String getQuantitaRam(int pos) {
        //return quantita.get(pos);
        System.out.println("OK!!!");
        return Arrays.toString(size);
    }

    public static void setQuantitaRam(int pos, Ram ram) {
        System.out.println("Ram creata!!!");
        size[pos] = ram;
    }
    
    
    
    public static void main(String[] args)
    {
        //quantita = new ArrayList<Cpu>();
        quantita = new Cpu[20];
        size = new Ram[20];
        System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
        
        
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8081).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new Ping());
        config.register(new CpuResource());
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
}
}
