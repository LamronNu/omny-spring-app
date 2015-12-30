<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: Olga
  Date: 30.12.2015
  Time: 11:23
--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>

<h1>Message: ${message}</h1>

<table class="table table-bordered table-hover table-striped" id="usersTable">
    <thead>
    <tr>
        <th>#</th>
        <th>id</th>
        <th>first name</th>
        <th>last name</th>
        <th>home address</th>
        <th>created on</th>
        <th>link</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user" varStatus="rowCounter">
        <tr>
            <td align="center"><b><c:out value="${rowNumber}"/></b></td>
            <td align="center"><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.homeAddress}"/></td>
            <td><c:out value="${user.createdDate}"/></td>
            <td>
                <strong>
                    <a href='/user/<c:out value="${user.id}"/>'>
                        go to user page
                    </a>
                </strong>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>
