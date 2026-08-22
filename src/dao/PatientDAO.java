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
    
    public boolean patientExists(String contactNumber) {
        boolean exists = false;
        String sql = "SELECT * FROM patient WHERE contact_number=?";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, contactNumber);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    exists = true;
                }
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Patient Lookup Error: " + e.getMessage());
        }
        return exists;
    }

    public boolean addPatient(Patient p) {
        String sql = "INSERT INTO patient (patient_id, name, address, contact_number) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, p.getPatientId());
            pst.setString(2, p.getName());
            pst.setString(3, p.getAddress());
            pst.setString(4, p.getContactNumber());
            
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Add Patient Error: " + e.getMessage());
            return false;
        }
    }
}