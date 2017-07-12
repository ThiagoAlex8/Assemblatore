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


import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.sun.net.httpserver.HttpServer;
import it.alessio.assemblatore.oauth.OpenId;
import it.alessio.assemblatore.resources.Cpu;
import it.alessio.assemblatore.resources.CpuResource;
import it.alessio.assemblatore.resources.HardDisk;
import it.alessio.assemblatore.resources.HardDiskResource;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import it.alessio.assemblatore.resources.Ping;
import it.alessio.assemblatore.resources.Ram;
import it.alessio.assemblatore.resources.RamResource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

public class Service {
    
    //private static ArrayList<Cpu> quantita;
    private static Cpu quantita[];
    private static Ram size[];
    private static HardDisk sizeHD[];
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    
    public static Cpu getQuantita(int pos) {
        //return quantita.get(pos);
        System.out.println("OK!!!");
        return quantita[pos];
    }

    public static void setQuantita(int pos, Cpu cpu) {
        System.out.println("Cpu creata!!!");
        quantita[pos] = cpu;
    }
    public static Ram getQuantitaRam(int pos) {
        //return quantita.get(pos);
        System.out.println("OK!!!");
        return size[pos];
    }

    public static void setQuantitaRam(int pos, Ram ram) {
        System.out.println("Ram creata!!!");
        size[pos] = ram;
    }
    
    public static void setQuantitaHD(int pos, HardDisk hd) {
        sizeHD[pos] = hd;
    }

    public static HardDisk getQuantitaHD(int pos) {
       return sizeHD[pos];
    }
    
    
    public static void main(String[] args) throws URISyntaxException, IOException
    {
        //quantita = new ArrayList<Cpu>();
        quantita = new Cpu[20];
        size = new Ram[20];
        sizeHD = new HardDisk[20];
        
        client.withRegion(Regions.EU_WEST_1);
        //client.setEndpoint("http://localhost/v1:8001");
        System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
        OpenId oid = new OpenId();
        String state = oid.flowStepOne();
        oid.flowStepTwo("openid email", state);
        System.out.print("Inserisci l'url: ");
        Scanner input = new Scanner(System.in);
        String url = input.nextLine();
        String response = oid.confirmResponse(url);
        //System.out.println(state + "   " + response);
        oid.StringControl(state, response);
        
        
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8081).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new Ping());
        config.register(new CpuResource(client));
        config.register(new RamResource(client));
        config.register(new HardDiskResource(client));
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
}

    
}
