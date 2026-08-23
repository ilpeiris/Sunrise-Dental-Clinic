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


//Fetches the completed bill to send to the printer
    public Bill getBillDetails(String billNo) {
        Bill bill = null;
        String sql = "SELECT * FROM bill WHERE bill_no = ?";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, billNo);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    bill = new Bill();
                    bill.setBillNo(rs.getString("bill_no"));
                    bill.setAppointmentId(rs.getInt("appointment_id"));
                    bill.setTotalCost(rs.getDouble("total_cost")); //total cost
                }
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Fetch Bill Error: " + e.getMessage());
        }
        return bill;
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


