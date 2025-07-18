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
        <c:if test="${empty sessionScope.loginUser}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
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
            <c:param name="key" value="${requestScope.key}"/>
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
                        <td>No</td>
                        <td>Id</td>
                        <td>Name</td>
                        <td>Description</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Status</td>
                        <td>Date</td>
                        <td>Time</td>
                        <td>Date time</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${requestScope.list}" varStatus="st">
                        <tr>
                            <td>${st.count}</td>
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
                            <td>${item.dateTest}</td>
                            <td>${item.timeTest}</td>
                            <td>${item.dateTimeTestDisplay}</td>
                            <td>
                                <form action="MainController" method="GET">
                                    <input type="hidden" name="key" value="${requestScope.key}" />
                                    <input type="hidden" name="id" value="${item.id}" />
                                    <input type="hidden" name="action" value="getItem" /> 
                                    <button type="submit">Update</button>
                                </form>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="key" value="${requestScope.key}" />
                                    <input type="hidden" name="id" value="${item.id}" />
                                    <input type="hidden" name="action" value="delete" /> 
                                    <button type="submit">Delete</button>
                                </form>
                                <c:url var="softDeleteUrl" value="MainController">
                                    <c:param name="key" value="${requestScope.key}"/>
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
            
        <br /> <br />
        
        ${requestScope.idError}
        ${requestScope.nameError}
        ${requestScope.descriptionError}
        ${requestScope.priceError}
        ${requestScope.quantityError}
        ${requestScope.statusError}
        ${requestScope.dateError}
        ${requestScope.timeError}
        ${requestScope.dateTimeError}
        
        <c:if test="${list != null && not empty list}">
            <table border="1" style="border-collapse: collapse; width: 100%">
                <thead>
                    <tr>
                        <td>No</td>
                        <td>Id</td>
                        <td>Name</td>
                        <td>Description</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Status</td>
                        <td>Date</td>
                        <td>Time</td>
                        <td>Date time</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${requestScope.list}" varStatus="st">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${st.count}</td>
                            <td>
                                ${item.id} <br />
                            </td>
                            <td>
                                <input type="text" name="name" value="${item.name}"/> <br />
                            </td>
                            <td>
                                <input type="text" name="description" value="${item.description}"/>  <br />
                            </td>
                            <td>
                                <input type="number" step="any" name="price" value="${item.price}"/> <br />
                            </td>
                            <td>
                                <input type="text" name="quantity" value="${item.quantity}"/> <br />
                            </td>
                            <td>
                                <select name="status">
                                    <option value="1" ${item.status ? "selected" : ""}>Available</option>
                                    <option value="0" ${!item.status ? "selected" : ""}>Out of stock</option>
                                </select> <br />
                            </td>
                            <td>
                                <input type="date" name="dateTest" value="${item.dateTest}"/>
                            </td>
                            <td>
                                <input type="time" name="timeTest" value="${item.timeTest}"/>
                            </td>
                            <td>
                                <input type="datetime-local" name="dateTimeTest" value="${item.dateTimeTest}"/>
                            </td>
                            <td>
                                <input type="hidden" name="key" value="${requestScope.key}" />
                                <input type="hidden" name="id" value="${item.id}" />
                                <input type="hidden" name="action" value="updateDirect" /> 
                                <button type="submit">Update</button>
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
