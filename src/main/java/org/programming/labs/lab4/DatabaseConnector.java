package org.programming.labs.lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector {

    private static final String DB_URL = "jdbc:h2:~/test"; // URL бази даних (наприклад, H2)
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public Connection establishConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            System.out.println("Attempting to connect to the database...");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection error! Please check your configuration.");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("H2 driver not found. Check dependencies.");
            return null;
        }
    }

    public QueryResultData executeSelectQuery(Connection connection, String sqlQuery) {
        if (connection == null) {
            System.err.println("The request cannot be fulfilled: no connection available.");
            return null;
        }

        List<Map<String, String>> rowData = new ArrayList<>();
        List<String> columnHeaders = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnHeaders.add(metaData.getColumnLabel(i));
            }

            while (resultSet.next()) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String header = columnHeaders.get(i - 1);
                    String value = resultSet.getString(i);
                    row.put(header, (value != null) ? value : "NULL");
                }
                rowData.add(row);
            }

            System.out.println("\n[SQL Query Executed: " + sqlQuery + "]");
            return new QueryResultData(columnHeaders, rowData);

        } catch (SQLException e) {
            System.err.println("Request execution error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
