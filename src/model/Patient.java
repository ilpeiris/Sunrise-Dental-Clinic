/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ilpeiris
 */
public class Patient extends Person{
    private String patientID;
    
    public Patient() {
        super();
    }
    
    public String getPatientID() { return patientID; }
    public void setPatientID(String paientID) { this.patientID = patientID; }
    
}
