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
        System.out.println("=====================================");
        System.out.println("       SUNRISE DENTAL CLINIC         ");
        System.out.println("=====================================");
        System.out.println("Bill No: " + newBill.getBillNo());
        System.out.println("Appointment ID Ref: " + newBill.getAppointmentId());
        System.out.println("Total Cost: LKR " + newBill.getTotalCost());
        System.out.println("=====================================");
        System.out.println("Thank you!.\n");
    }
}