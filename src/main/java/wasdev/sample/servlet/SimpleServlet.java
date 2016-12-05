package wasdev.sample.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClientURI;

import org.bson.Document;    
import org.bson.conversions.Bson;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Hello World!");
        
        CloudantClient client = ClientBuilder.account("a9d8c3a6-3006-49af-92e5-b3da10409a8c-bluemix")
                .username("a9d8c3a6-3006-49af-92e5-b3da10409a8c-bluemix")
                .password("f92cbd858f5958091cae58f6edc0e04bda5849b604461fae8ad8d96f5d4ce8d3")
                .build();
        System.out.println("Server Version: " + client.serverVersion());
        List<String> databases = client.getAllDbs();
        System.out.println("All my databases : ");
        for ( String db : databases ) {
            System.out.println(db);
        }
        
        System.out.println("CHL TEST: java home:" + System.getenv("java.home"));
        
        try
        {
        	MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        	build.connectionsPerHost(50);  
            //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
            build.threadsAllowedToBlockForConnectionMultiplier(50);  
            build.connectTimeout(1*60*1000);  
            build.maxWaitTime(2*60*1000);  
            MongoClientOptions options = build.build();  
        	MongoClientURI uri = new MongoClientURI("mongodb://admin:PZFNDYXJCNYXLDQD@bluemix-sandbox-dal-9-portal.3.dblayer.com:15792/admin?ssl=true",build);  
        	MongoClient mongoClient = new MongoClient(uri);
        	
        	MongoDatabase mongoDatabase = mongoClient.getDatabase("movie");  
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("user");  
            // insert a document  
            Document document = new Document("x", 1);  
            mongoCollection.insertOne(document);  
            document.append("x", 2).append("y", 3);  
  
            // replace a document  
            mongoCollection.replaceOne(Filters.eq("_id", document.get("_id")), document);  
  
            // find documents  
            List<Document> foundDocument = mongoCollection.find().into(new ArrayList<Document>());  
        	
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        
    }

}
