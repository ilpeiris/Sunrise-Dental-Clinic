/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ilpeiris
 */

public class Bill implements Printable {
    private String billNo;
    private int appointmentId;
    private double totalCost;
    
    private String patientName;
    private String contactNumber;
    
    private String appointmentNoStr; 
    private String dentistName;  

    private String email;    

    public Bill() {}

    public String getBillNo() { return billNo; }
    public void setBillNo(String billNo) { this.billNo = billNo; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    
    public String getAppointmentNoStr() { return appointmentNoStr; }
    public void setAppointmentNoStr(String appointmentNoStr) { this.appointmentNoStr = appointmentNoStr; }

    public String getDentistName() { return dentistName; }
    public void setDentistName(String dentistName) { this.dentistName = dentistName; }
    
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    
    @Override
    public String getPrintableDetails() {
        return "Bill No: " + this.billNo + " | Total: LKR " + this.totalCost;
    }
}
