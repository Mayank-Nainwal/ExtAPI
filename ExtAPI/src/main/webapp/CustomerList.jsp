<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- 
    Document   : details
    Created on : 19-Oct-2023, 12:00:58 pm
    Author     : HP
--%>

<%@page import="BasicServlet.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="java.io.*"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="javax.servlet.http.Cookie"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Details</title>
        <style>
            .btn-1{
                position: absolute;
            }
            .btn-2{
                position: relative;
                right:-55px;
                top:0px;
            }
            .btn{
                border: none;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>-Customer List Screen</h1>
            <a href="NewCustomer.jsp"><button>Add Customer</button></a>
            <a href="/ExtAPI/Logout"><button>Logout</button></a>
            <h3>Customer List</h3>
            <table style="border-spacing: 15px;">
                <tr>
                    <td>
                        First Name
                    </td>
                    <td>
                        Last Name
                    </td>
                    <td>
                        Address
                    </td>
                    <td>
                        City
                    </td>
                    <td>
                        State
                    </td>
                    <td>
                        Email
                    </td>
                    <td>
                        Phone
                    </td>
                    <td>
                        Action
                    </td>
                </tr>
<!--                Adding content to table-->
<!--            Rough Start-->
            <% 
            
            ObjectMapper objectMapper = new ObjectMapper();
			List<Customer> customers = null;
        	
        	// making request to get customer list
	    	String urlString = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
	    	
	    	URL url = new URL(urlString);  
	    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        
	        // Setting the token for request
	        conn.setRequestProperty("Authorization", "Bearer "+request.getAttribute("token") );
	    	
	        // adding cookie to response
	        Cookie cookie = new Cookie("Authorization",request.getAttribute("token").toString());
	        response.addCookie(cookie);
	        
        	try(BufferedReader br = new BufferedReader(
            		new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder res = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        res.append(responseLine.trim());
        		    }
        		    
        		    customers = objectMapper.readValue(res.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Customer.class));
        		    
        		    conn.disconnect();
        		    
        		   /* for(Customer customer : customers) {
        		    	System.out.println(customer);
        		    }*/
        		    
            }
        	catch(Exception e) {
        		
        		System.err.println("Issue in Fetching customer List");
    	    	RequestDispatcher view = request.getRequestDispatcher("credential.html");
    	    	view.forward(request, response);
            	
            }
            %>

<!--Rough End-->
            <%  for(Customer it : customers) { %>
                    <tr>
                        <td>
                            <%= it.getFirst_name()%>
                        </td>
                        <td>
                            <%= it.getLast_name()%>
                        </td>
                        <td>
                            <%= it.getAddress() %>
                        </td>
                        <td>
                            <%= it.getCity() %>
                        </td>
                        <td>
                            <%= it.getState() %>
                        </td>
                        <td>
                            <%= it.getEmail() %>
                        </td>
                        <td>
                            <%= it.getPhone() %>
                        </td>
                        <td>
                            <form method="post" action="UpdateCustomer.jsp">
                                        <input style="display:none;" type="text" name="uuid" value="<%= it.getUuid() %>" >
                                        <input style="display:none;" type="text" name="first_name" value=<%= it.getFirst_name() %> >
                                        <input style="display:none;" type="text" name="street" value=<%= it.getStreet() %> >
                                        <input style="display:none;" type="text" name="city" value=<%= it.getCity() %> >
                                        <input style="display:none;" type="email" name="email" value=<%= it.getEmail() %> >
                                        <input style="display:none;" type="text" name="last_name" value=<%= it.getLast_name() %> >
                                        <input style="display:none;" type="text" name="address" value=<%= it.getAddress() %> >
                                        <input style="display:none;" type="text" name="state" value=<%= it.getState() %> >
                                        <input style="display:none;" type="text" name="phone" value=<%= it.getPhone() %> >
                                        <button class="btn btn-1">update</button>
                            </form>
                                <form method="post" action="/ExtAPI/Delete">
                                        <input type="text" style="display:none;" name="uuid" value="<%= it.getUuid() %>" >
                                        <button class="btn btn-2">delete</button>
                                </form>
                        </td>
                    </tr>
                    <% } %>
            </table>
        </div>
    </body>
</html>