/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ilpeiris
 */
public class SMSNotification implements Notification {
    @Override
    public void notifyUser(String recipient, String message) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());

        System.out.println("\n=======================================================");
        System.out.println("  >>> OUTGOING SMS NOTIFICATION TRIGGERED <<<  ");
        System.out.println("=======================================================");
        System.out.println("  .----------------. ");
        System.out.println(" |  [ SMS ALERT ]   |");
        System.out.println(" |  ..............  |");
        System.out.println("  '-------. .------' ");
        System.out.println("          |/         ");
        System.out.println("=========================================");
        System.out.println("  Sender ID     : SUNRISE-DENTAL");
        System.out.println("  Timestamp     : " + timeStamp);
        System.out.println("  Recipient     : " + recipient);
        System.out.println("  Network Status: [DELIVERED - SUCCESS]");
        System.out.println("-------------------------------------------------------");
        System.out.println("  Message: ");
        System.out.println("  \"" + message + "\"");
        System.out.println("=======================================================\n");
    }
    
    
    // SMS cannot send pdfs, so make it ignore the file)
    @Override
    public void notifyUserWithAttachment(String recipient, String message, String filePath) {
        System.out.println("SMS does not support attachments. Sending standard SMS instead.");
        notifyUser(recipient, message);
    }
}