<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,com.example.projekt_sieci_3.Station" %>
<html>
<head>
    <title>Panel admina</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/main.css">

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">

            <div class="style padding: 25 px">
                <a class="navbar-brand" href="index.html">Strona główna</a>
            </div>

        </div>
    </div>
</nav>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>


<h1>Stacje krwiodawstwa</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nazwa</th>
        <th scope="col">Adres</th>
        <th scope="col">Miasto</th>
        <th scope="col">Strona internetowa</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="tmpStation" items="${STATIONS_LIST}">

        <%-- definiowanie linkow--%>
        <c:url var="updateLink" value="AdminServlet">
            <c:param name="command" value="LOAD"></c:param>
            <c:param name="stationID" value="${tmpStation.id}"></c:param>
        </c:url>

        <c:url var="deleteLink" value="AdminServlet">
            <c:param name="command" value="DELETE"></c:param>
            <c:param name="stationID" value="${tmpStation.id}"></c:param>
        </c:url>

        <c:url var="bloodLink" value="AdminServlet">
            <c:param name="command" value="LOAD_BLOOD"></c:param>
            <c:param name="stationID" value="${tmpStation.id}"></c:param>
        </c:url>


        <tr>
            <th scope="row">${tmpStation.id}</th>
            <td>${tmpStation.name}</td>
            <td>${tmpStation.address}</td>
            <td>${tmpStation.city}</td>
            <td>${tmpStation.wwwSite}</td>
            <td><a href="${bloodLink}">
                <button type="button" class="btn btn-success" style="background-color: mediumpurple;">Stan krwi</button>
            </a> </td>
            <td><a href="${updateLink}">
                <button type="button" class="btn btn-success">Zmień dane</button>
            </a>
            <a href="${deleteLink}"
               onclick="if(!(confirm('Czy na pewno chcesz usunąć tę stację krwiodawstwa?'))) return false">
                <button type="button" class="btn btn-danger">Usuń</button>
            </a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="col-sm-9">
    <p><a class="btn btn-primary btn-info" href="add_station_form.jsp" role="button">Dodaj stację</a></p>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
    <div class="container-fluid">

        <div class="col-sm-9">
            <a href="index.html" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do strony głównej</a>
        </div>
    </div>
</div>