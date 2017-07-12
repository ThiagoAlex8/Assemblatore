/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.oauth;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;


/**
 *
 * @author aless
 */
public class OpenId {
    private String client_id = "272007422240-29tll9eulbe3o9pnhtn43euvppjj061a.apps.googleusercontent.com";
    private String client_secret = "http://configuratore.s3-website-eu-west-1.amazonaws.com";
    private String redirect_uri = "https://www.getpostman.com/oauth2/callback";
    
    public String flowStepOne()
    { 
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        return state;
    }
    
    public void flowStepTwo(String scope, String state) throws URISyntaxException, IOException
    {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https")
                .setHost("accounts.google.com")
                .setPath("/o/oauth2/v2/auth")
                .setParameter("client_id", client_id)
                .setParameter("response_type", "code")
                .setParameter("scope", scope)
                .setParameter("redirect_uri", redirect_uri)
                .setParameter("state", state);
        URI uri = builder.build();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);
        System.out.println(get.getURI());
        //client.execute(get);
        HttpResponse response = client.execute(get);
        
        
    }
    
    public String confirmResponse(String s){
        Matcher matcher = Pattern.compile("(?<=state=).*?(?=&code)").matcher(s);
        String response = "";
        while(matcher.find()){
             response = matcher.group();
        }
        return response;
    }
    
    public void StringControl(String a, String b){
        if(a.equals(b)){
            System.out.println("OK");
        }
        else{
            System.out.println("NA"); 
        }
        
       
        
    }
    
    

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
    
    
}
