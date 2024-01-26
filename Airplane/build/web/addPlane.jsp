<%-- 
    Document   : addPlane
    Created on : Nov 29, 2023, 4:19:28 PM
    Author     : anhhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController">
            <label>ID:</label>
            <input type="text" name="id" placeholder="Enter ID Plane" required=""><br>
            <label>Destination</label>
            <input type="text" name="destination" placeholder="Enter destination" required=""><br>
            <label>DepartureTime</label>
            <input type="text" name="departureTime" placeholder="Enter departureTime" required=""><br>
            <label>isDelay</label>
            <input type="text" name="isDelay" placeholder="Enter true/false" required=""><br>
            <input type="submit" name="action" value="Save">
        </form>
    </body>
</html>
