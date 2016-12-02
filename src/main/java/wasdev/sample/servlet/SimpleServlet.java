package wasdev.sample.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

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
        
        CloudantClient client = ClientBuilder.account("a9d8c3a6-3006-49af-92e5-b3da10409a8c-bluemix.cloudant.com")
                .username("a9d8c3a6-3006-49af-92e5-b3da10409a8c-bluemix")
                .password("f92cbd858f5958091cae58f6edc0e04bda5849b604461fae8ad8d96f5d4ce8d3")
                .build();
        System.out.println("Server Version: " + client.serverVersion());
        List<String> databases = client.getAllDbs();
        System.out.println("All my databases : ");
        for ( String db : databases ) {
            System.out.println(db);
        }
    }

}
