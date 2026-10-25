/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ilpeiris
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;
import model.Dentist;

public class DentistDAO {
    
    
    
    
    public boolean addDentist(Dentist dentist) {
        String sql = "INSERT INTO dentist (dentist_id, name, address, contact_number, consultation_fee) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, dentist.getDentistId());
            pst.setString(2, dentist.getName());
            pst.setString(3, dentist.getAddress());
            pst.setString(4, dentist.getContactNumber());
            pst.setDouble(5, dentist.getConsultationFee());
            
            int rows = pst.executeUpdate();
            return rows > 0;
            
        } catch (Exception e) {
            System.out.println("Add Dentist Error: " + e.getMessage());
            return false;
        }
    }


// Auto-generate the next Dentist ID DEN00 
public String getAutoDentistId() {
    String newDentistId = "DEN001"; 
    String sql = "SELECT dentist_id FROM dentist ORDER BY id DESC LIMIT 1";
    
    try (java.sql.Connection con = db.DBConnection.getInstance();
         java.sql.Statement st = con.createStatement();
         java.sql.ResultSet rs = st.executeQuery(sql)) {
         
        if (rs.next()) {
            String lastNo = rs.getString("dentist_id"); 
            // extrct the number part after DEN
            int num = Integer.parseInt(lastNo.substring(3)); 
            newDentistId = String.format("DEN%03d", num + 1); 
        }
    } catch (Exception e) {
        System.out.println("Auto-Gen Dentist Error: " + e.getMessage());
    }
    return newDentistId;
}
}

