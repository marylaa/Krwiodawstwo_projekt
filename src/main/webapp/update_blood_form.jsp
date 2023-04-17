<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*,com.example.projekt_sieci_3.Station" %>
<html>
<head>
  <title>Zmiana stanu krwi</title>
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
    <h1>Zmień stan banku krwi</h1>

    <form action="AdminServlet" method="get">
      <input type="hidden" name="command" value="UPDATE_BLOOD"/>
      <input type="hidden" name="stationID" value="${STATION.id}"/>

<table class="table table-striped">

  <thead>
  <tr>
    <th scope="col"></th>
    <th scope="col"></th>
  </tr>
  </thead>
  <tbody>
    <tr>
      <td>Stan grupy krwi 0 Rh +:</td>
      <td>
        <select name="plus_0Input">
          <option value="Mamy zapas" ${STATION.blood0plus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.blood0plus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.blood0plus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi 0 Rh -:</td>
      <td>
        <select name="minus_0Input">
          <option value="Mamy zapas" ${STATION.blood0minus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.blood0minus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.blood0minus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi AB Rh +:</td>
      <td>
        <select name="plus_ABInput">
          <option value="Mamy zapas" ${STATION.bloodABplus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.bloodABplus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.bloodABplus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi AB Rh -:</td>
      <td>
        <select name="minus_ABInput">
          <option value="Mamy zapas" ${STATION.bloodABminus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.bloodABminus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.bloodABminus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi A Rh +:</td>
      <td>
        <select name="plus_AInput">
          <option value="Mamy zapas" ${STATION.bloodAplus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.bloodAplus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.bloodAplus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi A Rh -:</td>
      <td>
        <select name="minus_AInput">
          <option value="Mamy zapas" ${STATION.bloodAminus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.bloodAminus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.bloodAminus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi B Rh +:</td>
      <td>
        <select name="plus_BInput">
          <option value="Mamy zapas" ${STATION.bloodBplus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
          <option value="Potrzebujemy" ${STATION.bloodBplus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
          <option value="Pilna potrzeba" ${STATION.bloodBplus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
        </select><br>
      </td>
    </tr>
    <tr>
      <td>Stan grupy krwi B Rh -:</td>
      <td>
              <select name="minus_BInput">
                <option value="Mamy zapas" ${STATION.bloodBminus == 'Mamy zapas' ? 'selected' : ''}>Mamy zapas</option>
                <option value="Potrzebujemy" ${STATION.bloodBminus == 'Potrzebujemy' ? 'selected' : ''}>Potrzebujemy</option>
                <option value="Pilna potrzeba" ${STATION.bloodBminus == 'Pilna potrzeba' ? 'selected' : ''}>Pilna potrzeba</option>
              </select><br>
      </td>
    </tr>
  </tbody>
</table>

      <button type="submit" class="btn btn-success">Zmień dane</button>
    </form>
  </div>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
  <div class="container-fluid">

    <c:url var="loadBloodLink" value="AdminServlet">
      <c:param name="command" value="LOAD_BLOOD"></c:param>
      <c:param name="stationID" value="${STATION.id}"></c:param>
    </c:url>

    <div class="col-sm-9">
      <a href="${loadBloodLink}"><button type="button" class="btn btn-success">Wróć do stanu krwi</button></a>
    </div>
  </div>
</div>

</body>
</html>
