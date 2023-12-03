package com.example.myjavafxapp.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for establishing a connection to an SQLite database.
 */
public class SQLiteConnector {

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return The established database connection.
     */
    public static Connection connect() {
        // Path to the CSV file
        String csvFile = "src/main/resources/csv/E-Edictionary.csv";
        // Path to the SQLite database
        String jdbcUrl = "jdbc:sqlite:src/main/resources/engData.db";

        try {
            // Load the JDBC driver for SQLite
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl);

            // Print a success message
            System.out.println("Database connection successful.");

            // Return the established connection
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            // Print the exception details
            e.printStackTrace();
            return null;
        }
    }
}
