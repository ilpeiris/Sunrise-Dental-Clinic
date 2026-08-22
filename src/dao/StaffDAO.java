/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
        
        
import java.sql.*;
import db.DBConnection;
import exception.DatabaseConnectionException;

/**
 *
 * @author ilpeiris
 */

public class StaffDAO {
    public boolean login(String username, String password) {
        boolean status = false;
        String sql = "SELECT * FROM staff WHERE username=? AND password=?";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    status = true;
                }
            }
            
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Login Database Error: " + e.getMessage());
        }
        return status;
    }
    
    
}
