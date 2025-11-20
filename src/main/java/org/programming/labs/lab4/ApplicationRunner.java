package org.programming.labs.lab4;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationRunner {

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        QueryVisualizer visualizer = new QueryVisualizer();
        Connection databaseConnection = null;

        try {
            databaseConnection = connector.establishConnection();

            if (databaseConnection != null) {
                QueryResultData results;

                String query1 = "SELECT student_id, first_name, last_name, date_of_birth FROM students WHERE date_of_birth < '2004-01-01' ORDER BY date_of_birth DESC;";
                results = connector.executeSelectQuery(databaseConnection, query1);
                visualizer.displayResults(results);

                String query2 = "SELECT last_name, first_name, student_record_book_number, middle_name FROM students ORDER BY last_name ASC;";
                results = connector.executeSelectQuery(databaseConnection, query2);
                visualizer.displayResults(results);

                String query3 = "SELECT student_id, first_name, last_name, middle_name FROM students WHERE first_name = 'John' OR first_name = 'Emily';";
                results = connector.executeSelectQuery(databaseConnection, query3);
                visualizer.displayResults(results);

                String query4 = "SELECT first_name, last_name, date_of_birth FROM students WHERE last_name LIKE 'M%' OR last_name LIKE 'D%';";
                results = connector.executeSelectQuery(databaseConnection, query4);
                visualizer.displayResults(results);
            }

        } finally {
            if (databaseConnection != null) {
                try {
                    databaseConnection.close();
                    System.out.println("Connection successfully closed.");
                } catch (SQLException e) {
                    System.err.println("Connection closure error: " + e.getMessage());
                }
            }
        }
    }
}
