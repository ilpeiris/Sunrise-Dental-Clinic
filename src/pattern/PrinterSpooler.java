/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;
import model.Bill;
/**
 *
 * @author ilpeiris
 */
public class PrinterSpooler {
    
    private static PrinterSpooler instance;

    
    private PrinterSpooler() {
        System.out.println("Printer Spooler Initialized. Ready for print jobs.");
    }

    
    public static PrinterSpooler getInstance() {
        if (instance == null) {
            instance = new PrinterSpooler();
        }
        return instance;
    }


    
    public void printReceipt(Bill newBill) {
        String receiptContent = 
            "=====================================\n" +
            "       SUNRISE DENTAL CLINIC         \n" +
            "=====================================\n" +
            "Bill No: " + newBill.getBillNo() + "\n" +
            "Patient Name: " + newBill.getPatientName() + "\n" +
            "Contact: " + newBill.getContactNumber() + "\n" +
            "Appointment Ref ID: " + newBill.getAppointmentId() + "\n" +
            "Total Cost: LKR " + newBill.getTotalCost() + "\n" +
            "=====================================\n" +
            "Thank you! Please visit again.\n";
            
      
        System.out.println(receiptContent);

        // txt file
        try (java.io.FileWriter writer = new java.io.FileWriter("Bill_" + newBill.getBillNo() + ".txt")) {
            writer.write(receiptContent);
            System.out.println("File saved: Bill_" + newBill.getBillNo() + ".txt");
        } catch (java.io.IOException e) {
            System.out.println("File Write Error: " + e.getMessage());
        }
    }
}