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


    
    public void printReceipt(model.Bill newBill) {
        String receiptContent = 
            "========================================================================================================\n" +
            "                                                                                                        \n" +
            "                %%%%%%%%  %%%     %%%  %%%%     %%%  %%%%%%%%%   %%%   %%%%%%%  %%%%%%%%%               \n" +
            "               %%%%  %%   %%%     %%%  %%%%%    %%%  %%%% %%%%%  %%%  %%%%  %%  %%%%                    \n" +
            "               %%%%%%     %%%     %%%  %%%%%%%  %%%  %%%%  %%%%  %%%  %%%%%%    %%%%%%%%                \n" +
            "                %%%%%%%%  %%%     %%%  %%% %%%% %%%  %%%%%%%%%   %%%   %%%%%%%% %%%%%%%%                \n" +
            "                %   %%%%% %%%%    %%%  %%%  %%%%%%%  %%%%%%%%    %%%   %   %%%% %%%%                    \n" +
            "              %%%%%%%%%%   %%%%%%%%%   %%%    %%%%%  %%%%  %%%%  %%%  %%%%%%%%% %%%%%%%%%               \n" +
            "                %%%%%%      %%%%%%     %%%      %%%  %%%    %%%  %%%   %%%%%%   %%%%%%%%%               \n" +
            "                                                                                                        \n" +
            "========================================================================================================\n" +
            "Bill No: " + newBill.getBillNo() + "\n" +
            "Appointment No: " + newBill.getAppointmentNoStr() + "\n" +
            "Patient Name: " + newBill.getPatientName() + "\n" +
            "Contact: " + newBill.getContactNumber() + "\n" +
            "Attending Dentist: " + newBill.getDentistName() + "\n" +
            "Total Cost: LKR " + newBill.getTotalCost() + "\n" +
            "========================================================================================================\n" +
            "                               Thank you! Please visit again.                                           \n";
            
      
        System.out.println(receiptContent);

        // txt file
        java.io.FileWriter writer = null;
        try {
            
            java.io.File directory = new java.io.File("bills");
            if (!directory.exists()) {
                directory.mkdirs(); 
            }
            
           
            String filePath = "bills/Bill_" + newBill.getBillNo() + ".txt";
            
           
            writer = new java.io.FileWriter(filePath);
            writer.write(receiptContent);
            System.out.println("--> File saved successfully: " + filePath);
            
        } catch (java.io.IOException e) {
            System.out.println("File Write Error: " + e.getMessage());
            
        } finally {
           
            System.out.println("Executing finally block: Cleaning up file resources.");
            try {
                if (writer != null) {
                    writer.close(); 
                }
            } catch (java.io.IOException ex) {
                System.out.println("Error closing writer: " + ex.getMessage());
            }
        }
    }
}