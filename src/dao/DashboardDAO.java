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
public class DashboardDAO {
    
// total number of appointments
    public int getTotalAppointments() {
        int count = 0;
        //String sql = "SELECT COUNT(*) AS total FROM appointment";
        String sql = "SELECT COUNT(*) AS total FROM appointment WHERE appt_date = CURDATE()";
        
        try (Connection con = DBConnection.getInstance();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
             
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Analytics Error: " + e.getMessage());
        }
        return count;
    }
}