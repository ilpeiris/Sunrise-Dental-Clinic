/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author ilpeiris
 */
public class EmailNotification implements Notification {
    
    @Override
    public void notifyUser(String recipientEmail, String messageText) {
        
        // YOUR CREDENTIALS
        final String senderEmail = ""; // Put your real Gmail here
        final String appPassword = ""; // No spaces

        //SMTP SERVER
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // AUTHENTICATE
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        // SEND THE EMAIL
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            
            
            
            
            message.setSubject("Sunrise Dental: Appointment Confirmation");

            //  HTML email body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String htmlContent = "<html><body>"
                               + "<img src=\"cid:clinicLogo\" width=\"180\"><br/><br/>"
                               + "<span style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">"
                               + messageText.replace("\n", "<br/>")
                               + "</span>"
                               + "</body></html>";
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            //Inline Logo 
            MimeBodyPart logoPart = new MimeBodyPart();
            java.net.URL logoUrl = getClass().getResource("/view/images/logoT1.png");
            if (logoUrl != null) {
                javax.activation.DataSource fds = new javax.activation.URLDataSource(logoUrl);
                logoPart.setDataHandler(new javax.activation.DataHandler(fds));
                logoPart.setHeader("Content-ID", "<clinicLogo>");
                logoPart.setDisposition(MimeBodyPart.INLINE);
            }

            // Combine into a single email (No PDF)
            Multipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);
            if (logoUrl != null) {
                multipart.addBodyPart(logoPart); // Embeds the logo inline
            }

            //send
            message.setContent(multipart);
            Transport.send(message);
            
            
            String timeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new java.util.Date());

System.out.println("\n");
System.out.println("  >>> OUTGOING EMAIL NOTIFICATION TRIGGERED <<<  ");
System.out.println("=======================================================");
System.out.println("       .-------------------. ");
System.out.println("      |  .---------------.  |");
System.out.println("      |  |   [ EMAIL ]   |  |");
System.out.println("      |  '---------------'  |");
System.out.println("       '-------------------' ");
System.out.println("=======================================================");
System.out.println("  Sender        : SUNRISE-DENTAL SMTP");
System.out.println("  Timestamp     : " + timeStamp);
System.out.println("  Recipient     : " + recipientEmail);
System.out.println("  Network Status: [DELIVERED - SMTP SUCCESS]");
System.out.println("=======================================================");
System.out.println("  Subject : Sunrise Dental: Appointment Confirmation");
System.out.println("  Message : ");
System.out.println("  \"" + messageText + "\"");
System.out.println("=======================================================\n");
            
            

        } catch (MessagingException e) {
            System.out.println("Email Failed: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    //new method implementation inside EmailNotification.java
    @Override
    public void notifyUserWithAttachment(String recipientEmail, String messageText, String filePath) {
        final String senderEmail = "ilpeiris02@gmail.com"; 
        final String appPassword = "cohshuaostlsmsic"; 

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Sunrise Dental: Your Official Invoice");

            //  HTML email body part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            // convert standard text to html tags
            String htmlContent = "<html><body>"
                               + "<img src=\"cid:clinicLogo\" width=\"180\"><br/><br/>"
                               + "<span style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333333;\">"
                               + messageText.replace("\n", "<br/>")
                               + "</span>"
                               + "</body></html>";
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            // inline Logo 
            MimeBodyPart logoPart = new MimeBodyPart();
            java.net.URL logoUrl = getClass().getResource("/view/images/logoT1.png");
            if (logoUrl != null) {
                javax.activation.DataSource fds = new javax.activation.URLDataSource(logoUrl);
                logoPart.setDataHandler(new javax.activation.DataHandler(fds));
                logoPart.setHeader("Content-ID", "<clinicLogo>");
                logoPart.setDisposition(MimeBodyPart.INLINE);
            }

            // pdf attachment body part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new java.io.File(filePath));

            // Combine all
            Multipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);
            if (logoUrl != null) {
                multipart.addBodyPart(logoPart); 
            }
            multipart.addBodyPart(attachmentPart); 

            // send
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("\n+---------------------------------------------------------------+");
            System.out.println("|   _________                                                   |");
            System.out.println("|  |\\       /|    [ SYSTEM LOG ] SMTP GATEWAY                   |");
            System.out.println("|  | \\     / |                                                  |");
            System.out.println("|  |  \\___/  |    Status  : [ SUCCESS ]                         |");
            System.out.println("|  |_________|    Action  : Email Sent with PDF Attachment      |");
            System.out.println("|                 To      : " + String.format("%-35s", recipientEmail) + " |");
            System.out.println("+---------------------------------------------------------------+\n");

        } catch (Exception e) {
            System.out.println("Email Attachment Failed: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}