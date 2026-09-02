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
    private String patientId;
    private String email;
    
    public Patient() {
        super();
    }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email; } 
    
    @Override
    public String getRoleDescription() {
        return "Role: Registered Patient";
    }
}
