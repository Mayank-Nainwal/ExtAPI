package BasicServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie cookie = new Cookie("Authorization","");
        response.addCookie(cookie);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
