<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,com.example.projekt_sieci_3.Station" %>
<html>
<head>
  <title>Zmiana danych stacji</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="css/main.css">
</head>

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

<div class="jumbotron">
  <div class="container">
    <h1>Zmień dane stacji</h1>

    <form action="AdminServlet" method="get">
      <input type="hidden" name="command" value="UPDATE"/>
      <input type="hidden" name="stationID" value="${STATION.id}"/>
      <div class="form-group">
        <label for="Name">Nazwa</label>
        <input type="text" class="form-control" name="nameInput" value="${STATION.name}"/>
      </div>
      <div class="form-group">
        <label for="Address">Adres</label>
        <input type="text" class="form-control" name="addressInput" value="${STATION.address}"/>
      </div>
      <div class="form-group">
        <label for="City">Miasto</label>
        <input type="text" class="form-control" name="cityInput" value="${STATION.city}"/>
      </div>
      <div class="form-group">
        <label for="PhoneNumber">Numer telefonu</label>
        <input type="text" class="form-control" name="phoneNumberInput" value="${STATION.phoneNumber}"/>
      </div>
      <div class="form-group">
        <label for="wwSite">Strona internetowa</label>
        <input type="text" class="form-control" name="websiteInput" value="${STATION.wwwSite}"/>
      </div>
      <button type="submit" class="btn btn-success">Zmień dane</button>
    </form>
  </div>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
  <div class="container-fluid">

    <div class="col-sm-9">
      <a href="AdminServlet" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do zestawienia</a>
    </div>
  </div>
</div>

</body>
</html>
