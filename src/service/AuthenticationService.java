/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.StaffDAO;

/**
 *
 * @author ilpeiris
 */
public class AuthenticationService {
    private StaffDAO staffDAO;

    
    public AuthenticationService() {
        this.staffDAO = new StaffDAO();
    }

    public boolean authenticate(String username, String password) {
        return staffDAO.login(username, password);
    }
}