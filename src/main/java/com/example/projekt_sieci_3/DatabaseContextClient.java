package com.example.projekt_sieci_3;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseContextClient extends DatabaseContext{
    private DataSource dataSource;

    /**
     * Parametrowy kontruktor.
     *
     * @param dataSource obiekt klasy DataSource
     */
    public DatabaseContextClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Metoda służąca do pobierania listy stacji z bazy danych.
     *
     * @return lista obiektów typu Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    @Override
    public List<Station> getStations() throws Exception {
        List<Station> stations = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();

            String sql = "select * from objects";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String adress = resultSet.getString("adress");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");
                String wwwSite = resultSet.getString("website");
                String blood0plus = resultSet.getString("plus_0");
                String blood0minus = resultSet.getString("minus_0");
                String bloodABplus = resultSet.getString("plus_AB");
                String bloodABminus = resultSet.getString("minus_AB");
                String bloodAplus = resultSet.getString("plus_A");
                String bloodAminus = resultSet.getString("minus_A");
                String bloodBplus = resultSet.getString("plus_B");
                String bloodBminus = resultSet.getString("minus_B");

                stations.add(new Station(id, name, adress, city, phoneNumber, wwwSite, blood0plus, blood0minus, bloodABplus, bloodABminus, bloodAplus, bloodAminus, bloodBplus, bloodBminus));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return stations;
    }

    /**
     * Metoda służąca do pobrania stacji z bazy danych na podstawie id.
     *
     * @param id id stacji
     * @return obiekt typu Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    public Station getStation(String id) throws Exception {
        Station station = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            int stationID = Integer.parseInt(id);

            conn = dataSource.getConnection();
            String sql = "SELECT * FROM objects WHERE id =?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, stationID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String adress = resultSet.getString("adress");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");
                String wwwSite = resultSet.getString("website");
                String blood0plus = resultSet.getString("plus_0");
                String blood0minus = resultSet.getString("minus_0");
                String bloodABplus = resultSet.getString("plus_AB");
                String bloodABminus = resultSet.getString("minus_AB");
                String bloodAplus = resultSet.getString("plus_A");
                String bloodAminus = resultSet.getString("minus_A");
                String bloodBplus = resultSet.getString("plus_B");
                String bloodBminus = resultSet.getString("minus_B");

                station = new Station(stationID, name, adress, city, phoneNumber, wwwSite, blood0plus, blood0minus, bloodABplus, bloodABminus, bloodAplus, bloodAminus, bloodBplus, bloodBminus);
            } else {
                throw new Exception("Could not find station with id " + stationID);
            }
            return station;
        } finally {
            close(conn, statement, resultSet);
        }
    }

    /**
     * Metoda służąca do pobierania listy stacji z bazy danych na podstawie filtracji.
     *
     * @param parameter parametr filtrujący
     * @param filterInput dana filtrująca
     * @return lista obiektów typu Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    public List<Station> getFilteredStations(String parameter, String filterInput) throws Exception {
        List<Station> stations = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();

            String sql = "select * from objects where " + parameter + " like '%" + filterInput + "%'";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String adress = resultSet.getString("adress");
                String city = resultSet.getString("city");
                String phoneNumber = resultSet.getString("phone_number");
                String wwwSite = resultSet.getString("website");
                String blood0plus = resultSet.getString("plus_0");
                String blood0minus = resultSet.getString("minus_0");
                String bloodABplus = resultSet.getString("plus_AB");
                String bloodABminus = resultSet.getString("minus_AB");
                String bloodAplus = resultSet.getString("plus_A");
                String bloodAminus = resultSet.getString("minus_A");
                String bloodBplus = resultSet.getString("plus_B");
                String bloodBminus = resultSet.getString("minus_B");

                stations.add(new Station(id, name, adress, city, phoneNumber, wwwSite, blood0plus, blood0minus, bloodABplus, bloodABminus, bloodAplus, bloodAminus, bloodBplus, bloodBminus));
            }
        } finally {
            close(conn, statement, resultSet);
        }
        return stations;
    }
}
