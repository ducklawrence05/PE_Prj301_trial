<%-- 
    Document   : update
    Created on : Jul 6, 2025, 12:30:25 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.loginUser}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <h1>Update Page</h1>
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

        <form action="MainController" method="POST">
            <input type="hidden" name="key" value="${param.key}" />
            <input type="hidden" name="id" value="${electronic.id}" />
            <input type="hidden" name="action" value="update" />

            <label>ID:</label> <br />
            <div>${electronic.id}</div>
            ${requestScope.idError} <br />
            
            <label for="name">Name</label> <br />
            <input type="text" id="name" name="name" value="${electronic.name}"/>
            ${requestScope.nameError} <br />

            <label for="description">Description</label> <br />
            <input type="text" id="description" name="description" value="${electronic.description}"/>
            ${requestScope.descriptionError} <br />

            <label for="price">Price</label> <br />
            <input type="number" step="any" id="price" name="price" value="${electronic.price}"/>
            ${requestScope.priceError} <br />

            <label for="quantity">Quantity</label> <br />
            <input type="text" id="quantity" name="quantity" value="${electronic.quantity}"/>        
            ${requestScope.quantityError} <br />

            <label for="status">Status</label> <br />
            <select name="status">
                <option value="1" ${electronic.status ? "selected" : ""}>Available</option>
                <option value="0" ${!electronic.status ? "selected" : ""}>Out of stock</option>
            </select>
            ${requestScope.statusError}

            <br />
            <button type="submit">Update</button>
        </form>

        <c:url var="redirect" value="MainController">
            <c:param name="action" value="redirect" />
            <c:param name="url" value="search.jsp" />
        </c:url>
        <a href="${redirect}">Back to Search</a>
        
        <form action="MainController" method="GET">
            <input type="hidden" name="key" value="${param.key}" />
            <input type="hidden" name="action" value="search" />
            <button type="submit">Back to Search with key</button>
        </form>
    </body>
</html>
