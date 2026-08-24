/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ilpeiris
 */
public class Staff extends Person {
   private String username;
    private String password;

    public Staff() {
        super();
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    
    @Override
    public String getRoleDescription() {
        return "Role: Clinic Administrator";
    }
    
}