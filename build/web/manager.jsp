<%-- 
    Document   : manager.jsp
    Created on : Nov 29, 2023, 12:24:19 PM
    Author     : Kyrov
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Search</title>
    </head>
    <body>
        <h1>User Search</h1>

        <form action="MainController" method="post">
            <input type="text" name="searchPattern" value="${param.searchPattern}" placeholder="Search Customer" />
            <button type="submit" name="action" value="SearchCustomer">Search</button>
        </form>
        <h2>Search Results:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Role ID</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.LIST_CUSTOMER}">
                    <tr>
                        <td>${user.getUserID()}</td>
                        <td>${user.getFullName()}</td>
                        <td>${user.getRoleID()}</td>
                        <td>               
                            <form action="MainController" method="POST">
                                <button type="submit" name="action" value="DeleteUser">Delete</button>
                                <input type="hidden" name="userID" value="${user.getUserID()}" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

