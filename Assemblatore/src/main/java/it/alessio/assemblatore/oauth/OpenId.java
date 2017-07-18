/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.alessio.assemblatore.oauth;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import sun.net.www.http.HttpClient;


/**
 *
 * @author aless
 */
public class OpenId {
    private String client_id = "272007422240-29tll9eulbe3o9pnhtn43euvppjj061a.apps.googleusercontent.com";
    private String client_secret = "UcDPY7Vb5MoVboozR69WzRPH";
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
    
    public String confirmResponse(String s){ //
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
    
    public void exchangeCodeForAccessToken(String code) throws IOException, URISyntaxException{
        
        String urlParameters = "code=" + code + "&" + 
                               "client_id=" + client_id + "&" +
                               "client_secret=" + client_secret + "&" +
                               "redirect_uri=" + redirect_uri + "&" +
                               "grant_type=" + "authorization_code";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        String request        = "https://www.googleapis.com/oauth2/v4/token";
        URL    url            = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
            BufferedReader response = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while((line = response.readLine()) != null) {
            System.out.println(line);
        }
           
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    } 

    
    public String getCode(String s){
        Matcher matcher = Pattern.compile("(?<=code=).*?(?=&authuser)").matcher(s);
        String code = "";
        while(matcher.find()){
             code = matcher.group();
        }
        System.out.println(code);
        return code;
        
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
