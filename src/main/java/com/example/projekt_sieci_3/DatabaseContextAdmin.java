package com.example.projekt_sieci_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseContextAdmin extends DatabaseContext{

    private String URL;
    private String name;
    private String password;

    /**
     * Parametrowy konstruktor.
     *
     * @param URL String
     */
    public DatabaseContextAdmin(String URL) {
        this.URL = URL;
    }

    /**
     * Metoda służąca do pobierania listy stacji z bazy danych.
     *
     * @return lista obiektów typu Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    @Override
    List<Station> getStations() throws Exception {
        List<Station> stations = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

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
     * Metoda służąca do dodania nowej stacji do bazy danych.
     *
     * @param station obiekt klasy Station
     * @throws Exception jeśli wystąpi błąd podczas dodawania stacji do bazy danych
     */
    public void addStation(Station station) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "insert into stations(name, adress, city, phone_number, website) values(?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, station.getName());
            statement.setString(2, station.getAddress());
            statement.setString(3, station.getCity());
            statement.setString(4, station.getPhoneNumber());
            statement.setString(5, station.getWwwSite());
            statement.execute();

            String sql2 = "select id from stations where name like '" + station.getName() + "'";
            statement = conn.prepareStatement(sql2);
            resultSet = statement.executeQuery(sql2);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            String sql3 = "insert into blood_status(station_id, plus_0, minus_0, plus_AB, minus_AB, plus_A, minus_A, plus_B, minus_B) " +
                    "values(?,?,?,?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql3);
            statement.setInt(1, id);
            statement.setString(2, null);
            statement.setString(3, null);
            statement.setString(4, null);
            statement.setString(5, null);
            statement.setString(6, null);
            statement.setString(7, null);
            statement.setString(8, null);
            statement.setString(9, null);
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /**
     * Metoda służąca do pobrania stacji z bazy danych na podstawie id.
     *
     * @param id id stacji
     * @return obiekt klasy Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    public Station getStation(String id) throws Exception {
        Station station = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            int stationID = Integer.parseInt(id);

            conn = DriverManager.getConnection(URL, name, password);
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
     * Metoda służąca do aktualizacji stacji w bazie danych.
     *
     * @param station obiekt klasy Station
     * @throws Exception jeśli wystąpi błąd aktualizacji stacji w bazie danych
     */
    public void updateStation(Station station) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "update stations set name=?, adress=?, city=?," +
                    "phone_number=?, website=? where id=?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, station.getName());
            statement.setString(2, station.getAddress());
            statement.setString(3, station.getCity());
            statement.setString(4, station.getPhoneNumber());
            statement.setString(5, station.getWwwSite());
            statement.setString(6, String.valueOf(station.getId()));
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /**
     * Metoda służąca do aktualizacji stanu krwi w bazie danych.
     *
     * @param station obiekt klasy Station
     * @throws Exception jeśli wystąpi błąd aktualizacji stanu krwi w bazie danych
     */
    public void updateBlood(Station station) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "update blood_status set plus_0=?, minus_0=?, plus_AB=?, minus_AB=?, plus_A=?, minus_A=?, plus_B=?, minus_B=?" +
                    " where station_id=?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, station.getBlood0plus());
            statement.setString(2, station.getBlood0minus());
            statement.setString(3, station.getBloodABplus());
            statement.setString(4, station.getBloodABminus());
            statement.setString(5, station.getBloodAplus());
            statement.setString(6, station.getBloodAminus());
            statement.setString(7, station.getBloodBplus());
            statement.setString(8, station.getBloodBminus());
            statement.setInt(9, station.getId());
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /**
     * Metoda służąca do usunięcia stacji z bazy danych.
     *
     * @param id id stacji
     * @throws Exception jeśli wystąpi błąd usuwania stacji z bazy danych
     */
    public void deleteStation(String id) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            int stationID = Integer.parseInt(id);

            conn = DriverManager.getConnection(URL, name, password);

            String sql2 = "delete from blood_status where station_id=?";
            statement = conn.prepareStatement(sql2);
            statement.setInt(1, stationID);
            statement.execute();

            String sql = "delete from stations where id =?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, stationID);
            statement.execute();
        } finally {
            close(conn, statement, null);
        }
    }

    /**
     * Setter dla nazwy.
     *
     * @param name nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter dla hasła.
     *
     * @param password hasło
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
