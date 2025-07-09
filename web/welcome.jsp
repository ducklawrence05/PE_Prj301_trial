<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code here-->
        <c:if test="${not empty sessionScope.loginUser}">
            Welcome ${sessionScope.loginUser.fullName} !
        </c:if>

        <c:if test="${not empty requestScope.msg}">
            ${requestScope.msg}
        </c:if>

        <c:if test="${not empty requestScope.error}">
            ${requestScope.error}
        </c:if>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="logout" />
            <button type="submit">Logout</button>
        </form>

        <c:url var="redirect" value="MainController">
            <c:param name="action" value="redirect" />
            <c:param name="url" value="search.jsp" />
        </c:url>
        <a href="${redirect}">Search</a>
    </body>
</html>
