/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.Appointment;
import db.DBConnection;
import exception.DatabaseConnectionException;


/**
 *
 * @author ilpeiris
 */


public class AppointmentDAO {
    
    public boolean addAppointment(Appointment appt) {
        String sql = "INSERT INTO appointment (appointment_no, appt_date, appt_time, patient_id, dentist_id, treatment_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, appt.getAppointmentNo());
            pst.setDate(2, appt.getApptDate());
            pst.setString(3, appt.getApptTime());
            pst.setInt(4, appt.getPatientId());
            pst.setInt(5, appt.getDentistId());
            pst.setInt(6, appt.getTreatmentId());
            
            int rows = pst.executeUpdate();
            return rows > 0;
            
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Add Appointment Error: " + e.getMessage());
            return false; 
        }
    }
    
    

    public java.sql.ResultSet getAllAppointments() {
        java.sql.ResultSet rs = null;
        try {
            java.sql.Connection con = db.DBConnection.getInstance();
            
            String sql = "SELECT a.appointment_no, a.appt_date, a.appt_time, p.name " +
                         "FROM appointment a JOIN patient p ON a.patient_id = p.id";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Fetch Appointments Error: " + e.getMessage());
        }
        return rs;
    }
    
}