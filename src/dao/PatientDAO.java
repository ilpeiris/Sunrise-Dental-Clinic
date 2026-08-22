/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.Patient;
import db.DBConnection;
import exception.DatabaseConnectionException;

/**
 *
 * @author ilpeiris
 */
public class PatientDAO {
    
    // Checks if patient exists and returns their Database ID
    public int getPatientIdByContact(String contactNumber) {
        int id = -1;
        String sql = "SELECT id FROM patient WHERE contact_number=?";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, contactNumber);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Patient Lookup Error: " + e.getMessage());
        }
        return id;
    }

    // Adds a new patient and returns their newly generated Database ID
    public int addPatientAndGetId(Patient p) {
        String sql = "INSERT INTO patient (patient_id, name, address, contact_number) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getInstance();
             // Request MySQL to return the auto-generated keys
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pst.setString(1, p.getPatientId());
            pst.setString(2, p.getName());
            pst.setString(3, p.getAddress());
            pst.setString(4, p.getContactNumber());
            
            pst.executeUpdate();
            
            // Retrieve the generated ID
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Add Patient Error: " + e.getMessage());
        }
        return -1;
    }
}