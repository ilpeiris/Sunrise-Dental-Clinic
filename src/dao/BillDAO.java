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
}