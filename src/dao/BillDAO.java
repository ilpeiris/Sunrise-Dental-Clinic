/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.*;
import model.Bill;
import db.DBConnection;
import exception.DatabaseConnectionException;

/**
 *
 * @author ilpeiris
 */


public class BillDAO {
    
    public boolean generateBill(String appointmentNo, String billNo) {
        //// Calls stored procedure inside db
        String sql = "{CALL sp_GenerateBill(?, ?)}";
        
        try (Connection con = DBConnection.getInstance();
             CallableStatement cst = con.prepareCall(sql)) {
             
            cst.setString(1, appointmentNo);
            cst.setString(2, billNo);
            
            cst.execute();
            return true;
            
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Generate Bill Procedure Error: " + e.getMessage());
            return false;
        }
    }



    public Bill getBillDetails(String billNo) {
        Bill bill = null;
        String sql = "SELECT b.bill_no, a.appointment_no, b.total_cost, p.name AS patient_name, p.contact_number, d.name AS dentist_name, p.email " +
                     "FROM bill b " +
                     "JOIN appointment a ON b.appointment_id = a.id " +
                     "JOIN patient p ON a.patient_id = p.id " +
                     "JOIN dentist d ON a.dentist_id = d.id " +
                     "WHERE b.bill_no = ?";
        
        try (java.sql.Connection con = db.DBConnection.getInstance();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, billNo);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    bill = new Bill();
                    bill.setBillNo(rs.getString("bill_no"));
                    bill.setAppointmentNoStr(rs.getString("appointment_no"));
                    bill.setTotalCost(rs.getDouble("total_cost"));
                    bill.setPatientName(rs.getString("patient_name"));           
                    bill.setContactNumber(rs.getString("contact_number")); 
                    bill.setDentistName(rs.getString("dentist_name"));   
                    bill.setEmail(rs.getString("email"));
                }
            }
        } catch (Exception e) {
            System.out.println("Fetch Bill Error: " + e.getMessage());
        }
        return bill;
    }
    
    // fetches bills for the hstry table
    public java.sql.ResultSet getAllBills() {
        java.sql.ResultSet rs = null;
        String sql = "SELECT b.bill_no, a.appointment_no, p.name, b.total_cost " +
                     "FROM bill b " +
                     "JOIN appointment a ON b.appointment_id = a.id " +
                     "JOIN patient p ON a.patient_id = p.id";
        try {
            java.sql.Connection con = db.DBConnection.getInstance();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.println("Fetch Bill History Error: " + e.getMessage());
        }
        return rs;
    }
    
    // Auto generate the next Bill Number
    public String getAutoBillNo() {
        String newBillNo = "B001";
        String sql = "SELECT bill_no FROM bill ORDER BY id DESC LIMIT 1";
        
        try (java.sql.Connection con = db.DBConnection.getInstance();
             java.sql.Statement st = con.createStatement();
             java.sql.ResultSet rs = st.executeQuery(sql)) {
             
            if (rs.next()) {
                String lastNo = rs.getString("bill_no"); 
                int num = Integer.parseInt(lastNo.substring(1)); 
                newBillNo = String.format("B%03d", num + 1); 
            }
        } catch (Exception e) {
            System.out.println("Auto-Gen Bill Error: " + e.getMessage());
        }
        return newBillNo;
    }
    
    
    
}


