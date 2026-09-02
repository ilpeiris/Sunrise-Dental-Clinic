/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;

/**
 *
 * @author ilpeiris
 */
public interface Notification {
    void notifyUser(String recipient, String message);
    // method for pdf attachments
    void notifyUserWithAttachment(String recipient, String message, String filePath);
}