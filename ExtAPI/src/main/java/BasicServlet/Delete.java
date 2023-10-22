package BasicServlet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		
		
		String uuid = request.getParameter("uuid");
		
		String urlString = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+uuid;
		
		HttpURLConnection conn=null;
        
        //Declare the connection to Auth API url
        URL url = new URL(urlString);  
        conn = (HttpURLConnection)url.openConnection();  
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer "+cookie.getValue().toString());
        if(conn.getResponseCode() == 200) {
        	System.out.println("delete working : " + uuid);
        }
        else if(conn.getResponseCode() == 500) {
        	System.out.println("Error not deleted" );
        }
        else if(conn.getResponseCode() == 400) {
        	System.out.println("UUID not found" );
        }
        else {
        	System.out.println(conn.getResponseCode());
        }
        
        
        request.setAttribute("token",cookie.getValue());
    	
    	
    	RequestDispatcher view = request.getRequestDispatcher("CustomerList.jsp");
    	view.forward(request, response);
	}

}
