/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;
import model.Bill;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.Element;


/**
 *
 * @author ilpeiris
 */
public class PrinterSpooler {
    
    private static PrinterSpooler instance;

    
    private PrinterSpooler() {
        System.out.println("Printer Spooler Initialized. Ready for PDF print jobs.");
    }

    
    public static PrinterSpooler getInstance() {
        if (instance == null) {
            instance = new PrinterSpooler();
        }
        return instance;
    }


    
    
    
    public void printReceipt(Bill newBill) {
        // makesure the directory exists
        File directory = new File("bills");
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        String filePath = "bills/Bill_" + newBill.getBillNo() + ".pdf";
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // header logo
            try {
                
                java.net.URL logoUrl = getClass().getResource("/view/images/logoT1.png");
                if (logoUrl != null) {
                    Image logo = Image.getInstance(logoUrl);
                    logo.scaleToFit(150, 150); 
                    logo.setAlignment(Element.ALIGN_CENTER); // Center
                    document.add(logo);
                }
            } catch (Exception imgEx) {
                System.out.println("Could not load PDF logo: " + imgEx.getMessage());
            }

            // email body
            // center clinic name under the logo
            Paragraph header = new Paragraph("SUNRISE DENTAL CLINIC");
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            Paragraph divider = new Paragraph("==========================================");
            divider.setAlignment(Element.ALIGN_CENTER);
            document.add(divider);
            document.add(new Paragraph("\n")); 
            
  
            document.add(new Paragraph("Bill No: " + newBill.getBillNo()));
            document.add(new Paragraph("Appointment No: " + newBill.getAppointmentNoStr()));
            document.add(new Paragraph("Patient Name: " + newBill.getPatientName()));
            document.add(new Paragraph("Contact: " + newBill.getContactNumber()));
            document.add(new Paragraph("Attending Dentist: " + newBill.getDentistName()));
            document.add(new Paragraph("\n------------------------------------------"));
            document.add(new Paragraph("Total Cost: LKR " + newBill.getTotalCost()));
            document.add(new Paragraph("=========================================="));
            
            Paragraph footer = new Paragraph("Thank you! Please visit again.");
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(new Paragraph("\n"));
            document.add(footer);

            System.out.println("\n+---------------------------------------------------------------+");
            System.out.println("|    _____                                                      |");
            System.out.println("|   /    /|_      [ SYSTEM LOG ] PRINTER SPOOLER                |");
            System.out.println("|  /___ / | |                                                   |");
            System.out.println("|  |PDF|  |/      Status  : [ SUCCESS ]                         |");
            System.out.println("|  |___| /        Action  : PDF Invoice Generated               |");
            System.out.println("|                 File    : " + String.format("%-35s", filePath) + " |");
            System.out.println("+---------------------------------------------------------------+\n");

        } catch (Exception e) {
            System.out.println("PDF Generation Error: " + e.getMessage());
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
    
    
    
    
    
    