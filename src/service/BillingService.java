/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import model.Bill;
import pattern.PrinterSpooler;
import pattern.Notification;
import pattern.NotificationFactory;

/**
 *
 * @author ilpeiris
 */

public class BillingService {
    private BillDAO billDAO;

    public BillingService() {
        this.billDAO = new BillDAO();
    }

    public boolean processBilling(String appointmentNo, String billNo) {
        // generates the bill via Stored Procedure
        boolean isGenerated = billDAO.generateBill(appointmentNo, billNo);
        
        if (isGenerated) {
            // Fetch the fully calculated bill from the database
            Bill printBill = billDAO.getBillDetails(billNo);
            
           
            if (printBill != null) {
                // generates the pdf
                PrinterSpooler.getInstance().printReceipt(printBill);
                
                //email the pdf to the patient
                String pdfPath = "bills/Bill_" + printBill.getBillNo() + ".pdf";
                String msg = "Dear " + printBill.getPatientName() + ",\n\n"
                           + "Thank you for visiting Sunrise Dental Clinic. "
                           + "Please find attached your official invoice for Appointment " 
                           + printBill.getAppointmentNoStr() + ".\n\n"
                           + "Warm Regards,\nSunrise Dental Management";

                Notification emailAlert = NotificationFactory.getNotification("EMAIL");
                if (emailAlert != null && printBill.getEmail() != null) {
                    emailAlert.notifyUserWithAttachment(printBill.getEmail(), msg, pdfPath);
                }               
            }
        }
        return isGenerated;
    }
}