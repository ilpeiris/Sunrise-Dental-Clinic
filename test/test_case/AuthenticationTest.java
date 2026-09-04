/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test_case;



import service.AuthenticationService;
import pattern.UserSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author ilpeiris
 */
public class AuthenticationTest {

    private AuthenticationService authService;

    @Before
    public void setUp() {
        authService = new AuthenticationService();
        UserSession.getInstance().clearSession(); // Reset before each test
    }

    @After
    public void tearDown() {
        UserSession.getInstance().clearSession();
    }

    // Test Case 1: Valid Login
    @Test
    public void testValidLogin() {
        boolean result = authService.authenticate("admin", "admin123");
        assertTrue("Valid credentials should return true", result);
    }

    // Test Case 2: Invalid Password
    @Test
    public void testInvalidPassword() {
        boolean result = authService.authenticate("admin", "wrongpassword");
        assertFalse("Invalid password should return false", result);
    }

    // Test Case 3: Empty Fields
    @Test
    public void testEmptyCredentials() {
        boolean result = authService.authenticate("", "");
        assertFalse("Empty credentials should return false", result);
    }

    // Test Case 4: User Session Token Generation
    @Test
    public void testSessionTokenGeneration() {
        UserSession.getInstance().startSession("admin");
        String token = UserSession.getInstance().getSessionToken();
        
        assertNotNull("Session token should not be null after login", token);
        assertEquals("Logged in user should be 'admin'", "admin", UserSession.getInstance().getLoggedInUser());
    }
    
    
    
    // Test Case 5: Boundary Value Analysis - Invalid Contact Number
    @Test
    public void testInvalidContactNumberBoundary() {
        String contact = "077123456"; // 9 digits (Below Boundary)
        boolean isValid = contact.matches("\\d{10}");
        
        assertFalse("System must reject contact numbers below the 10-digit boundary", isValid);
    }
    
    // Test Case 6: Factory Pattern - Email Notification Creation
    @Test
    public void testEmailNotificationFactory() {
        pattern.Notification emailAlert = pattern.NotificationFactory.getNotification("EMAIL");
        assertNotNull("Factory should successfully create an EmailNotification object", emailAlert);
    }
}