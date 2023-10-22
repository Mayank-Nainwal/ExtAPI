<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <style>
        .btn{
            margin-left: 22%;
        }
    </style>
</head>
<body>
    <%  String uuid = request.getParameter("uuid");
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone"); %>
        <h1 style="font-family: sans-serif;">Update Customer Details</h1>
        <form method="post" action="/ExtAPI/Update">
            <table>
            
                <tr>
                    <td><input type="text" name="first_name" value= <%= first_name %> required><br></td>
                    <td><input type="text" name="last_name" value=<%= last_name %>  required><br></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" value=<%= street %>  required><br></td>
                    <td><input type="text" name="address" value=<%= address %>  required><br></td>
                </tr>
                <tr>
                    <td><input type="text" name="city" value=<%= city %>  required><br></td>
                    <td><input type="text" name="state" value=<%= state %>  required><br></td>
                </tr>
                <tr>
                    <td><input type="email" name="email" value=<%= email %> required><br></td>
                    <td><input type="text"  name="phone" value=<%= phone %> required><br></td>
                </tr>
            </table>
            <input type="text" style="display:none;" name="uuid" value= <%= uuid %> required>
            <button class="btn">Submit</button>
        </form>
        
</body>
</html>