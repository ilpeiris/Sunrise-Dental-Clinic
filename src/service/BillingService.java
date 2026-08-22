/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import model.Bill;
import pattern.PrinterSpooler;

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
                PrinterSpooler.getInstance().printReceipt(printBill);
            }
        }
        return isGenerated;
    }
}