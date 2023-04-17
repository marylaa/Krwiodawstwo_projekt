<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,com.example.projekt_sieci_3.Station" %>
<html>
<head>
    <title>Stacje krwiodawstwa</title>
</head>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/main.css">

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">

            <div class="style padding: 25px">
                <a class="navbar-brand" href="index.html">Strona główna</a>
            </div>

        </div>
    </div>
</nav>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>


<h1>Nasze stacje</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<form action="ClientServlet" method="get">
    <input type="hidden" name="command" value="FILTER"/>

<select name="filter-by" style="height: 30px;">
    <option value="city">Miasto</option>
    <option value="name">Nazwa</option>
</select>
<input type="text" name="filterInput" size="10" style="height: 30px;"/>

    <button type="submit" class="btn btn-success" style="height: 30px; background-color: mediumpurple;">Filtruj</button>
</form>

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

        <c:url var="bloodLink" value="ClientServlet">
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
                <button type="button" class="btn btn-success">Stan krwi</button>
            </a>
        </tr>
    </c:forEach>
    </tbody>
</table>

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


</body>
</html>
