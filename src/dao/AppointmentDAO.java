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
            
            String sql = "SELECT a.appointment_no, a.appt_date, a.appt_time, p.name AS patient_name, d.name AS dentist_name, t.treatment_type " +
                         "FROM appointment a " +
                         "JOIN patient p ON a.patient_id = p.id " +
                         "JOIN dentist d ON a.dentist_id = d.id " +
                         "JOIN treatment t ON a.treatment_id = t.id " +
                         "ORDER BY a.appt_date DESC, a.appt_time ASC";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Fetch Appointments Error: " + e.getMessage());
        }
        return rs;
    }
    
    
    
    public java.sql.ResultSet searchAppointmentByDate(String dateStr) {
        java.sql.ResultSet rs = null;
        try {
            java.sql.Connection con = db.DBConnection.getInstance();
            String sql = "SELECT a.appointment_no, a.appt_date, a.appt_time, p.name AS patient_name, d.name AS dentist_name, t.treatment_type " +
                         "FROM appointment a " +
                         "JOIN patient p ON a.patient_id = p.id " +
                         "JOIN dentist d ON a.dentist_id = d.id " +
                         "JOIN treatment t ON a.treatment_id = t.id " +
                         "WHERE a.appt_date = ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dateStr);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Date Filter Error: " + e.getMessage());
        }
        return rs;
    }
    
    
    
    
    
    
    //Auto-generate Appointment no 
    public String getAutoAppointmentNo() {
        String newApptNo = "A001"; 
        String sql = "SELECT appointment_no FROM appointment ORDER BY id DESC LIMIT 1";
        
        try (java.sql.Connection con = db.DBConnection.getInstance();
             java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(sql)) {
             
            if (rs.next()) {
                String lastNo = rs.getString("appointment_no"); 
                int num = Integer.parseInt(lastNo.substring(1)); 
                newApptNo = String.format("A%03d", num + 1); 
            }
        } catch (Exception e) {
            System.out.println("Auto-Gen Appt Error: " + e.getMessage());
        }
        return newApptNo;
    }

    // Search appointment
    public java.sql.ResultSet searchAppointment(String keyword) {
        java.sql.ResultSet rs = null;
        try {
            java.sql.Connection con = db.DBConnection.getInstance();
            // Searches with appt no, patient name, contact number
            String sql = "SELECT a.appointment_no, a.appt_date, a.appt_time, p.name " +
                         "FROM appointment a JOIN patient p ON a.patient_id = p.id " +
                         "WHERE a.appointment_no LIKE ? OR p.name LIKE ? OR p.contact_number LIKE ?";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            pst.setString(3, "%" + keyword + "%");
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Search Appointments Error: " + e.getMessage());
        }
        return rs;
    }
    
    
    
    
   //todays pending appt in Billing Form
    public java.sql.ResultSet getPendingAppointmentsToday() {
        java.sql.ResultSet rs = null;
        try {
            java.sql.Connection con = db.DBConnection.getInstance();
            
            String sql = "SELECT a.appointment_no, p.name, a.appt_time, " +
                         "(d.consultation_fee + t.treatment_cost) AS expected_total " +
                         "FROM appointment a " +
                         "JOIN patient p ON a.patient_id = p.id " +
                         "JOIN dentist d ON a.dentist_id = d.id " +
                         "JOIN treatment t ON a.treatment_id = t.id " +
                         "WHERE a.appt_date = CURDATE() " +
                         "AND a.id NOT IN (SELECT appointment_id FROM bill)";
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Pending Appts Error: " + e.getMessage());
        }
        return rs;
    }
    
    
    
    
}