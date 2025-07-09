<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Login</h1>
            
        <c:if test="${not empty requestScope.error}">
            ${requestScope.error}
        </c:if>
        
        <form action="MainController" method="POST">
            <label for="userId">UserID: </label>
            <input type="text" id="userID" name="userID"/> <br />
            
            <label for="password">Password: </label>
            <input type="text" id="password" name="password"/> <br />
            
            <button type="submit" name="action" value="login">Login</button>
        </form>
    </body>
</html>
