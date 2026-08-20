/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.DatabaseConnectionException;
/**
 *
 * @author ilpeiris
 */

public class DBConnection {
    
    private static Connection instance;

private DBConnection() {
    System.out.println("Database Connection Manager Created");
}


public static Connection getInstance() throws DatabaseConnectionException {
    try {
        if (instance == null || instance.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            instance = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sunrise_dental_db",
                    "root", ""
            );
            System.out.println("Connected to Sunrise Dental Database");
        }
    } catch (ClassNotFoundException | SQLException e) {
        throw new DatabaseConnectionException("Database connection Failed: " + e.getMessage());
    }
    return instance;
}
}