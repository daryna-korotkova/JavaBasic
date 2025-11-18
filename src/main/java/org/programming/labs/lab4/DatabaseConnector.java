package org.programming.labs.lab4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";

    public Connection connect() {
        Connection connection = null;
        try {

            System.out.println("Attempting to connect to the database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection failed! Check the console output for details.");
            e.printStackTrace();
            return null;
        }
    }

    public void executeSelectQuery(Connection connection, String sqlQuery) {
        if (connection == null) {
            System.err.println("Cannot execute query: Connection is null.");
            return;
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            System.out.println("\n--- Query Results ---");

            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s |", resultSet.getMetaData().getColumnLabel(i));
            }
            System.out.println("\n------------------------------------------------");

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-20s |", resultSet.getString(i));
                }
                System.out.println();
            }
            System.out.println("------------------------------------------------\n");

        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = null;

        try {
            connection = connector.connect();

            if (connection != null) {
                String query = "SELECT student_id, first_name, last_name, date_of_birth FROM students LIMIT 5;";
                connector.executeSelectQuery(connection, query);
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
