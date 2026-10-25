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
        
        String hashedPassword = hashPassword(password);
        
        String sql = "SELECT * FROM staff WHERE username=? AND password=?";
        
        try (java.sql.Connection con = db.DBConnection.getInstance();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    status = true;
                }
            }
            
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Login Database Error: " + e.getMessage());
        }
        return status;
    }
    
    
    
    

    public boolean addStaff(String name, String address, String contact, String username, String password) {
       
        String hashedPassword = hashPassword(password);
        
        String sql = "INSERT INTO staff (name, address, contact_number, username, password) VALUES (?, ?, ?, ?, ?)";
        
        try (java.sql.Connection con = db.DBConnection.getInstance();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setString(3, contact);
            pst.setString(4, username);
            pst.setString(5, hashedPassword);
            
            int rows = pst.executeUpdate(); 
            return rows > 0; 
            
        } catch (Exception e) {
            System.out.println("Add Staff Error: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
        
    
    // SHA-256 Hash Method 
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString(); //  Hash return 
        } catch (Exception e) {
            System.out.println("Hashing Error: " + e.getMessage());
            return null;
        }
    }
    
    
    
    
    
}
