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

            // write pdf content
            document.add(new Paragraph("=========================================="));
            document.add(new Paragraph("          SUNRISE DENTAL CLINIC           "));
            document.add(new Paragraph("=========================================="));
            document.add(new Paragraph("Bill No: " + newBill.getBillNo()));
            document.add(new Paragraph("Appointment No: " + newBill.getAppointmentNoStr()));
            document.add(new Paragraph("Patient Name: " + newBill.getPatientName()));
            document.add(new Paragraph("Contact: " + newBill.getContactNumber()));
            document.add(new Paragraph("Attending Dentist: " + newBill.getDentistName()));
            document.add(new Paragraph("------------------------------------------"));
            document.add(new Paragraph("Total Cost: LKR " + newBill.getTotalCost()));
            document.add(new Paragraph("=========================================="));
            document.add(new Paragraph("      Thank you! Please visit again.      "));

            System.out.println(" PDF saved successfully: " + filePath);

        } catch (Exception e) {
            System.out.println("PDF Generation Error: " + e.getMessage());
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
    
    
    
    
    
    