<%-- 
    Document   : paymentSucces
    Created on : 10-nov-2015, 12:25:50
    Author     : Baya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Succes</title>
    </head>
    <body>
        <h1>Payment success!</h1>
        <p><%= request.getParameter("TOKEN") %></p>
                
    </body>
</html>
