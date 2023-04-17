package com.example.projekt_sieci_3;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {

    private DataSource dataSource;
    private DatabaseContextClient databaseContextClient;

    /**
     * Bezparametrowy konstruktor, używany do uzyskania dostępu do źródła danych.
     */
    public ClientServlet() {
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/krew");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda inicjalizuje servlet.
     *
     * @throws ServletException w przypadku błędu inicjalizacji
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            databaseContextClient = new DatabaseContextClient(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Metoda jest implementacją metody doGet() interfejsu HttpServlet, która obsługuje żądania HTTP typu GET.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String command = request.getParameter("command");
            if (command == null)
                command = "LIST";
            switch (command) {
                case "LIST":
                case "LIST_BLOOD":
                    listStations(command, request, response);
                    break;
                case "LOAD_BLOOD":
                    loadBloodStatus(request, response);
                    break;
                case "FILTER":
                    filterStations(request, response);
                    break;
                default:
                    listStations("LIST", request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        List<Station> stationsList = databaseContextClient.getStations();
        request.setAttribute("STATIONS_LIST", stationsList);

        RequestDispatcher dispatcher = null;
        if(command.equals("LIST")) {
            dispatcher = request.getRequestDispatcher("/client_view.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/client_blood_view.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * Metoda pobiera przefiltrowaną listę stacji z bazy danych i przekierowuje żądanie HTTP do widoku client_view.jsp.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void filterStations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String parameter = request.getParameter("filter-by");
        String filterInput = request.getParameter("filterInput");
        List<Station> stationsList = databaseContextClient.getFilteredStations(parameter, filterInput);

        request.setAttribute("STATIONS_LIST", stationsList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/client_view.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Metoda pobiera stację z bazy danych na podstawie id i przekierowuje żądanie HTTP do widoku client_blood_view.jsp.
     *
     * @param request obiekt klasy HttpServletRequest
     * @param response obiekt klasy HttpServletResponse
     * @throws Exception gdy występuje błąd w wykonywanej operacji
     */
    private void loadBloodStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("stationID");

        Station station = databaseContextClient.getStation(id);
        request.setAttribute("STATION", station);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/client_blood_view.jsp");
        dispatcher.forward(request, response);
    }
}
