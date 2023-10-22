package BasicServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Create
 */
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Getting customer credentials from response
		ObjectMapper objectMapper = new ObjectMapper();

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("Authorization") && !c.getValue().equals("")) {
					System.out.println("Auth cookie has been found with value : "+c.getValue());
					cookie  = c;
					
				}
			}
		}
		if(cookie != null) {
			System.out.println("Trying to create a new Entry");
			
			String first_name = request.getParameter("first_name");
	        String last_name = request.getParameter("last_name");
	        String street = request.getParameter("street");
	        String address = request.getParameter("address");
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        
	        NewCustomer customer = new NewCustomer(first_name,last_name,street,address,city,state,email,phone);
	       
	        
	        String jsonInputString = objectMapper.writeValueAsString(customer);
	        System.out.println(jsonInputString);
	        //String jsonInputString = "{\"first_name\" :\""+ first_name +"\", \"last_name\" : \""+last_name+"\" , \"street\" : \""+street+"\", \"address\" : \""+address+"\", \"city\" : \""+city+"\", \"state\" : \""+state+"\", \"email\" : \""+email+"\" , \"phone\" : \""+phone+"\"}";
			// Preparing for request to Auth API
			String urlString = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
			
			HttpURLConnection conn=null;
	        
	        //Declare the connection to Auth API url
	        URL url = new URL(urlString);  
	        conn = (HttpURLConnection)url.openConnection();  
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        
	        // Setting Bearer Token
	        conn.setRequestProperty("Authorization", "Bearer "+cookie.getValue().toString());
	        
//	        System.out.println(cookie.getValue().toString());
	        
	        // sending Request body
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setDoOutput(true);
	        try(OutputStream os = conn.getOutputStream()) {
	            byte[] input = jsonInputString.getBytes("utf-8");
	            os.write(input, 0, input.length);			
	        }
	        catch(Exception e) {
	        	System.err.println("Cannot send the Request");
	        }
	        
	        if(conn.getResponseCode() == 201) {
	        	System.out.println("Successfully inserted");
	        }
	        else if(conn.getResponseCode() == 400) {
	        	System.out.println("Missing First name and Last name");
	        }
	        else {
	        	System.out.println(conn.getResponseCode());
	        	try(BufferedReader br = new BufferedReader(
		        		new InputStreamReader(conn.getInputStream(), "utf-8"))) {
		    		    StringBuilder res = new StringBuilder();
		    		    String responseLine = null;
		    		    while ((responseLine = br.readLine()) != null) {
		    		        res.append(responseLine.trim()+"\n");
		    		    }
		    		    System.out.println(res);
		        }
	        }
	        
	        
	        
	        
	        
	        conn.disconnect();
		    
	    	request.setAttribute("token",cookie.getValue());
	    	
	    	
	    	RequestDispatcher view = request.getRequestDispatcher("CustomerList.jsp");
	    	view.forward(request, response);
	    	
		}
		else {
			response.getWriter().println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Login Page</title>\r\n"
					+ "    <Style>\r\n"
					+ "        h1,form {\r\n"
					+ "                text-align: center;\r\n"
					+ "            }\r\n"
					+ "    </Style>\r\n"
					+ "    \r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"c\">\r\n"
					+ "    <h1 style=\"font-family: sans-serif;\">Login Page</h1>\r\n"
					+ "    <form method=\"POST\" action=\"/ExtAPI/BasicResourceProvider\">\r\n"
					+ "        <input type=\"email\" class=\"form-control\" name=\"email\" placeholder=\"Login id\" required><br><br>\r\n"
					+ "        <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Password\" required><br><br>\r\n"
					+ "        <button>Submit</button>\r\n"
					+ "    </form>\r\n"
					+ "    </div>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}
	}
		
		
		
}


