<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: Olga
  Date: 30.12.2015
  Time: 11:23
--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<title>Welcome page</title>

<body>

<h1>List of users</h1>

<table class="table table-bordered table-hover table-striped" id="usersTable">
    <thead>
    <tr>
        <th>id</th>
        <th>first name</th>
        <th>last name</th>
        <th>home address</th>
        <th>created on</th>
        <th>link</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td align="center"><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.homeAddress}"/></td>
            <td><joda:format value="${user.createdDate}" pattern="dd.MM.yyyy"/></td>
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

<h1>Run batch job</h1>

<form:form commandName="batchJob" cssClass="form-inline" method="post" action="/run">

    <div class="form-group">
        <label for="daysCount" class="control-label">daysCount:</label>
        <form:input path="daysCount" cssClass="form-control" size="25"/>
        <input type="submit" value="Run!" class="btn btn-lg btn-primary"/>
    </div>

</form:form>

</body>

</html>
