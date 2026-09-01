/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;

/**
 *
 * @author ilpeiris
 */
public class SMSNotification implements Notification {
    @Override
    public void notifyUser(String recipient, String message) {
        System.out.println("=========================================");
        System.out.println("  .----------------. ");
        System.out.println(" |  [ SMS ALERT ]   |");
        System.out.println(" |  ..............  |");
        System.out.println("  '-------. .------' ");
        System.out.println("          |/         ");
        System.out.println("=========================================");
        System.out.println("To: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("=========================================\n");
    }
}