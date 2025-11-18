package org.programming.labs.lab4;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class Database {

    private static final String DB_URL = "jdbc:h2:~/test"; // Приклад для H2
    private static final String USER = "sa";
    private static final String PASS = "";

    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            System.out.println("Attempting to connect to the database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Connection failed! Check the console output for details.");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Driver not found. Please check your dependencies.");
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

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String[]> data = new ArrayList<>();
            String[] headers = new String[columnCount];
            int[] columnWidths = new int[columnCount];

            for (int i = 0; i < columnCount; i++) {
                headers[i] = metaData.getColumnLabel(i + 1);
                columnWidths[i] = headers[i].length();
            }

            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    String value = resultSet.getString(i + 1);
                    row[i] = (value != null) ? value : "NULL";
                    if (row[i].length() > columnWidths[i]) {
                        columnWidths[i] = row[i].length();
                    }
                }
                data.add(row);
            }

            System.out.println("\n--- Query Results for: " + sqlQuery + " ---");


            final String HORIZONTAL_LINE = "─";
            final String VERTICAL_LINE = "│";
            final String INTERSECTION = "┼";

            Runnable drawTopBorder = () -> {
                StringBuilder line = new StringBuilder("┌");
                for (int i = 0; i < columnCount; i++) {
                    line.append(HORIZONTAL_LINE.repeat(columnWidths[i] + 2)); // Ширина + 2 пробіли
                    line.append(i < columnCount - 1 ? "┬" : "┐"); // Кут або T-перетин
                }
                System.out.println(line);
            };

            Runnable drawLineSeparator = () -> {
                StringBuilder line = new StringBuilder("├");
                for (int i = 0; i < columnCount; i++) {
                    line.append(HORIZONTAL_LINE.repeat(columnWidths[i] + 2));
                    line.append(i < columnCount - 1 ? INTERSECTION : "┤");
                }
                System.out.println(line);
            };

            Runnable drawBottomBorder = () -> {
                StringBuilder line = new StringBuilder("└");
                for (int i = 0; i < columnCount; i++) {
                    line.append(HORIZONTAL_LINE.repeat(columnWidths[i] + 2));
                    line.append(i < columnCount - 1 ? "┴" : "┘");
                }
                System.out.println(line);
            };

            drawTopBorder.run();


            for (int i = 0; i < columnCount; i++) {
                System.out.printf(VERTICAL_LINE + " %-" + columnWidths[i] + "s ", headers[i]);
            }
            System.out.println(VERTICAL_LINE);


            drawLineSeparator.run();


            for (String[] row : data) {
                for (int i = 0; i < columnCount; i++) {
                    System.out.printf(VERTICAL_LINE + " %-" + columnWidths[i] + "s ", row[i]);
                }
                System.out.println(VERTICAL_LINE);
            }


            drawBottomBorder.run();
            System.out.println("(" + data.size() + " rows returned)\n");

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

                String query1 = "SELECT student_id, first_name, last_name, date_of_birth FROM students WHERE date_of_birth < '2004-01-01' ORDER BY date_of_birth DESC;";
                connector.executeSelectQuery(connection, query1);

                String query2 = "SELECT last_name, first_name, student_record_book_number, middle_name FROM students ORDER BY last_name ASC;";
                connector.executeSelectQuery(connection, query2);

                String query3 = "SELECT student_id, first_name, last_name, middle_name FROM students WHERE first_name = 'John' OR first_name = 'Emily';";
                connector.executeSelectQuery(connection, query3);


                String query4 = "SELECT first_name, last_name, date_of_birth FROM students WHERE last_name LIKE 'M%' OR last_name LIKE 'D%';";
                connector.executeSelectQuery(connection, query4);
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
