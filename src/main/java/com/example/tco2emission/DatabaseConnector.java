package com.example.tco2emission;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//the connection to be use in the other classes
public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/co2emissions";
    private static final String USER = "root";
    private static final String PASS = "Liz@mor26";

    public DatabaseConnector() {
    }


    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/co2emissions", "root", "Liz@mor26");
        } catch (SQLException var2) {
            throw new RuntimeException("Error connecting to the database", var2);
        }
    }
}
