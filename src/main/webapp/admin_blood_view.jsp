<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,com.example.projekt_sieci_3.Station" %>
<html>
<head>
  <title>Stan banku krwi</title>
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


<h1>Stan banku krwi</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<table class="table table-striped">

  <thead>
  <tr>
    <th scope="col">Krew 0 Rh+</th>
    <th scope="col">Krew 0 Rh-</th>
    <th scope="col">Krew AB Rh+</th>
    <th scope="col">Krew AB Rh-</th>
    <th scope="col">Krew A Rh+</th>
    <th scope="col">Krew A Rh-</th>
    <th scope="col">Krew B Rh+</th>
    <th scope="col">Krew B Rh-</th>
  </tr>
  </thead>
  <tbody>
  <input type="hidden" name="command" value="LIST_BLOOD"/>
  <input type="hidden" name="stationID" value="${STATION.id}"/>

  <c:url var="updateBloodLink" value="AdminServlet">
    <c:param name="command" value="LOAD_UPDATE_BLOOD"></c:param>
    <c:param name="stationID" value="${STATION.id}"></c:param>
  </c:url>

  <tr>
    <td>${STATION.blood0plus}</td>
    <td>${STATION.blood0minus}</td>
    <td>${STATION.bloodABplus}</td>
    <td>${STATION.bloodABminus}</td>
    <td>${STATION.bloodAplus}</td>
    <td>${STATION.bloodAminus}</td>
    <td>${STATION.bloodBplus}</td>
    <td>${STATION.bloodBminus}</td>
    <td><a href="${updateBloodLink}">
      <button type="button" class="btn btn-success">Zmień stan</button>
    </a> </td>
  </tr>
  </tbody>
</table>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
  <div class="container-fluid">

    <div class="col-sm-9">
      <a href="AdminServlet" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do stacji</a>
    </div>
  </div>
</div>


</body>
</html>

