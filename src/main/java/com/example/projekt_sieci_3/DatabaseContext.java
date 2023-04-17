package com.example.projekt_sieci_3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class DatabaseContext {

    /**
     * Metoda abstrakcyjna służąca do pobierania listy stacji z bazy danych.
     *
     * @return lista obiektów typu Station
     * @throws Exception jeśli wystąpi błąd podczas pobierania stacji z bazy danych
     */
    abstract List<Station> getStations() throws Exception;

    /**
     * Metoda służąca do zamknięcia połączenia z bazą danych poprzez zwolnienie zasobów takich jak połączenie,
     * instrukcja i wynik zapytania. W przypadku wystąpienia wyjątku metoda wypisze informacje o nim.
     *
     * @param conn połączenie z bazą danych
     * @param statement instrukcja zapytania SQL
     * @param resultSet wynik zapytania SQL
     */
    protected static void close(Connection conn, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
