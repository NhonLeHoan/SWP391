<%-- 
    Document   : addPlane
    Created on : Nov 29, 2023, 8:03:09 PM
    Author     : Kyrov
--%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Information</title>
    </head>
    <body>
        <h1>Flight Information Form</h1>

        <c:if test="${not empty requestScope.ADD_FAIL}">
            <div style="color: red;">
                ${requestScope.ADD_FAIL}
            </div>
        </c:if>

        <form action="MainController" method="POST">
            <label for="id">Flight ID:</label>
            <input type="text" id="id" name="id" pattern="[A-Za-z0-9]{1,10}" required>
            <!-- Mã chuyến bay có thể chứa chữ cái và số, tối đa 10 ký tự -->

            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" required>

            <label for="departureTime">Departure Time:</label>
            <input type="datetime-local" id="departureTime" name="departureTime" required>
            <!-- Sử dụng input datetime-local để chọn ngày và giờ -->

            <button type="submit" name="action" value="AddAPlane">Submit</button>
        </form>
    </body>
</html>

