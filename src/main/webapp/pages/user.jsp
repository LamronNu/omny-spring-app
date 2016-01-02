<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>User Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
</head>
<body>

<h1>User Information</h1>

<table class="table table-bordered table-hover table-striped" id="usersTable">
    <thead>
    <tr>
        <td align="center"><b>field</b></td>
        <th>value</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="center"><b>id</b></td>
        <td>${user.id}</td>
    </tr>
    <tr>
        <td align="center"><b>first name</b></td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td align="center"><b>last name</b></td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td align="center"><b>home address</b></td>
        <td>${user.homeAddress}</td>
    </tr>
    <tr>
        <td align="center"><b>createdDate</b></td>
        <td><joda:format value="${user.createdDate}" pattern="dd.MM.yyyy"/></td>
    </tr>

    </tbody>
</table>

</body>
</html>
