<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Search Electronic</h1>

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
            <c:param name="url" value="create.jsp" />
        </c:url>
        <a href="${redirect}">Create</a>

        <form action="MainController" method="GET">
            <label for="key">Search by name:</label>
            <input type="text" name="key" id="key" value="${requestScope.key}" />
            <input type="submit" name="action" value="search" />
        </form>

        <c:if test="${list != null && not empty list}">
            <table border="1" style="border-collapse: collapse; width: 100%">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Name</td>
                        <td>Description</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Status</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${requestScope.list}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.description}</td>
                            <td>${item.price}</td>
                            <td>${item.quantity}</td>
                            <td>
                                <c:if test="${item.status}">
                                    Available
                                </c:if>
                                <c:if test="${!item.status}">
                                    Out of stock
                                </c:if>
                            </td>
                            <td>
                                <form action="MainController" method="GET">
                                    <input type="hidden" name="id" value="${item.id}" />
                                    <input type="hidden" name="action" value="getItem" /> 
                                    <button type="submit">Update</button>
                                </form>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="id" value="${item.id}" />
                                    <input type="hidden" name="action" value="delete" /> 
                                    <button type="submit">Delete</button>
                                </form>
                                <c:url var="softDeleteUrl" value="MainController">
                                    <c:param name="action" value="softDelete" />
                                    <c:param name="id" value="${item.id}"/>
                                </c:url>
                                <a href="${softDeleteUrl}">Soft Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>
