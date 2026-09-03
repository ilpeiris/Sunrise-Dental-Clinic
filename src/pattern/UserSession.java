/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ilpeiris
 */
public class UserSession {
    private static UserSession instance;
    private String loggedInUser;
    private String loginTime;
    private String sessionToken;


    private UserSession() {}


    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void startSession(String username) {
        this.loggedInUser = username;
        
        // Generate unique session token
        this.sessionToken = UUID.randomUUID().toString();
        
        // Format the current time
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        this.loginTime = formatter.format(new Date());
    }

    public void clearSession() {
        this.loggedInUser = null;
        this.loginTime = null;
        this.sessionToken = null; 
    }

    public String getLoggedInUser() { return loggedInUser; }
    public String getLoginTime() { return loginTime; }
    public String getSessionToken() { return sessionToken; }
}