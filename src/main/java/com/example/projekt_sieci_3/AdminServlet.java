package com.example.projekt_sieci_3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private DatabaseContextAdmin databaseContextAdmin;
    private final String dbUrl = "jdbc:mysql://localhost:3306/krwiodawstwo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";

    /**
     * Metoda inicjalizuje servlet.
     *
     * @param config obiekt klasy ServletConfig
     * @throws ServletException w przypadku błędu inicjalizacji
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            databaseContextAdmin = new DatabaseContextAdmin(dbUrl);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Metoda sprawdza poprawność danych logowania i przekierowuje żądanie do odpowiedniego widoku w zależności od wyniku weryfikacji.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws ServletException gdy wystąpi błąd w żądaniu HTTP
     * @throws IOException gdy wystąpi błąd wejścia/wyjścia
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");

        databaseContextAdmin.setName(name);
        databaseContextAdmin.setPassword(password);

        if (validate(name, password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");

            List<Station> stations = null;
            try {
                stations = databaseContextAdmin.getStations();
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("STATIONS_LIST", stations);

            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.include(request, response);
        }
    }

    /**
     * Metoda jest implementacją metody doGet() interfejsu HttpServlet, która obsługuje żądania HTTP typu GET.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws ServletException gdy wystąpi błąd w żądaniu HTTP
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            // odczytanie zadania
            String command = request.getParameter("command");
            if (command == null)
                command = "LIST";
            switch (command) {
                case "LIST":
                case "LIST_BLOOD":
                    listStations(command, request, response);
                    break;
                case "ADD":
                    addStations(request, response);
                    break;
                case "LOAD":
                case "LOAD_BLOOD":
                case "LOAD_UPDATE_BLOOD":
                    loadStation(command, request, response);
                    break;
                case "UPDATE":
                    updateStation(request, response);
                    break;
                case "UPDATE_BLOOD":
                    updateBlood(request, response);
                    break;
                case "DELETE":
                    deleteStation(request, response);
                    break;
                default:
                    listStations("LIST", request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Metoda usuwająca stację z bazy danych.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void deleteStation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("stationID");
        databaseContextAdmin.deleteStation(id);

        listStations("LIST", request, response);
    }

    /**
     * Metoda aktualizująca stację w bazie danych.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void updateStation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("stationID"));
        String name = request.getParameter("nameInput");
        String adress = request.getParameter("addressInput");
        String city = request.getParameter("cityInput");
        String phoneNumber = request.getParameter("phoneNumberInput");
        String wwwSite = request.getParameter("websiteInput");

        Station station = new Station(id, name, adress, city, phoneNumber, wwwSite);
        databaseContextAdmin.updateStation(station);

        listStations("LIST", request, response);
    }

    /**
     * Metoda aktualizująca stan krwi danej stacji w bazie danych.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void updateBlood(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("stationID"));
        String blood0plus = request.getParameter("plus_0Input");
        String blood0minus = request.getParameter("minus_0Input");
        String bloodABplus = request.getParameter("plus_ABInput");
        String bloodABminus = request.getParameter("minus_ABInput");
        String bloodAplus = request.getParameter("plus_AInput");
        String bloodAminus = request.getParameter("minus_AInput");
        String bloodBplus = request.getParameter("plus_BInput");
        String bloodBminus = request.getParameter("minus_BInput");

        Station station = new Station(id, blood0plus, blood0minus, bloodABplus, bloodABminus, bloodAplus, bloodAminus, bloodBplus, bloodBminus);
        databaseContextAdmin.updateBlood(station);
        request.setAttribute("stationID", station.getId());
        loadStation("LOAD_BLOOD", request, response);
    }

    /**
     * Metoda pobiera stację z bazy danych i przekierowuje żądanie HTTP do odpowiedniego widoku.
     *
     * @param command parametr
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void loadStation(String command, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("stationID");

        Station station = databaseContextAdmin.getStation(id);
        request.setAttribute("STATION", station);

        RequestDispatcher dispatcher = null;
        if(command.equals("LOAD")) {
            dispatcher = request.getRequestDispatcher("/update_station_form.jsp");
        } else if(command.equals("LOAD_BLOOD")) {
            dispatcher = request.getRequestDispatcher("/admin_blood_view.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/update_blood_form.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * Metoda dodaje stację do bazy danych.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void addStations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("nameInput");
        String adress = request.getParameter("addressInput");
        String city = request.getParameter("cityInput");
        String phoneNumber = request.getParameter("phoneNumberInput");
        String wwwSite = request.getParameter("websiteInput");
        String blood0plus = request.getParameter("plus_0Input");
        String blood0minus = request.getParameter("minus_0Input");
        String bloodABplus = request.getParameter("plus_ABInput");
        String bloodABminus = request.getParameter("minus_ABInput");
        String bloodAplus = request.getParameter("plus_AInput");
        String bloodAminus = request.getParameter("minus_AInput");
        String bloodBplus = request.getParameter("plus_BInput");
        String bloodBminus = request.getParameter("minus_BInput");

        Station station = new Station(name, adress, city, phoneNumber, wwwSite, blood0plus, blood0minus, bloodABplus, bloodABminus, bloodAplus, bloodAminus, bloodBplus, bloodBminus);
        databaseContextAdmin.addStation(station);

        listStations("LIST", request, response);
    }

    /**
     * Metoda pobiera listę stacji z bazy danych i przekierowuje żądanie HTTP do odpowiedniego widoku.
     *
     * @param command parametr
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void listStations(String command, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Station> stations = databaseContextAdmin.getStations();

        request.setAttribute("STATIONS_LIST", stations);

        RequestDispatcher dispatcher = null;
        if(command.equals("LIST")) {
            dispatcher = request.getRequestDispatcher("/admin_view.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/admin_blood_view.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * Metoda używana do połączenia z bazą danych i zwracająca wartość boolean w zależności od poprawności nawiązania połączenia.
     *
     * @param name nazwa
     * @param pass hasło
     * @return boolean
     */
    private boolean validate(String name, String pass) {
        boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbUrl, name, pass);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}

