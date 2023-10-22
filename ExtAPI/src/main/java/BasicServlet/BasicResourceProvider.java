package BasicServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BasicResourceProvider
 */
public class BasicResourceProvider extends HttpServlet {
	
	int reqCount = 0;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicResourceProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();

		
		// Getting customer credentials from response
		String email = request.getParameter("email");
        String password = request.getParameter("password");
		
		// Preparing for request to Auth API
		String urlString = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
		
		HttpURLConnection conn=null;
        
        //Declare the connection to Auth API url
        URL url = new URL(urlString);  
        conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        
        // Preparing the JSON body
        String jsonInputString = "{\"login_id\" : \""+ email +"\",\"password\" :\""+ password +"\"}";
        System.out.println("."+email + "-" + password +".");
        // sending Request body
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        catch(Exception e) {
        	System.err.println("Cannot parse the JSON Data");
        }
        
        Token token = null;
        
        // receiving Response Body and parsing it
        try(BufferedReader br = new BufferedReader(
    		new InputStreamReader(conn.getInputStream(), "utf-8"))) {
		    StringBuilder res = new StringBuilder();
		    String responseLine = null;
		    while ((responseLine = br.readLine()) != null) {
		        res.append(responseLine.trim());
		    }
		    
		    conn.disconnect();
		    
	    	token = objectMapper.readValue(res.toString(), Token.class);    	
	    	request.setAttribute("token",token.getAccess_token());
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("CustomerList.jsp");
	    	view.forward(request, response);
        }
        catch(Exception e) {	
        	System.err.println("Issue in Getting Auth Token due to incorrect Credential");
	    	RequestDispatcher view = request.getRequestDispatcher("relogin.jsp");
	    	view.forward(request, response);
        	
        }    	
          
        System.out.println("Request Served is : "+ (++reqCount));
        
	}

}
