/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pattern;

import model.Treatment;

/**
 *
 * @author ilpeiris
 */
public class TreatmentFactory {
    

    public static Treatment getTreatment(String type) {
        Treatment t = new Treatment();
        
        if (type.equalsIgnoreCase("General Cleaning")) {
            t.setTreatmentId("TRT001");
            t.setTreatmentType("General Cleaning");
            t.setTreatmentCost(2500.00);
        } else if (type.equalsIgnoreCase("Tooth Extraction")) {
            t.setTreatmentId("TRT002");
            t.setTreatmentType("Tooth Extraction");
            t.setTreatmentCost(4000.00);
        } else if (type.equalsIgnoreCase("Root Canal")) {
            t.setTreatmentId("TRT003");
            t.setTreatmentType("Root Canal");
            t.setTreatmentCost(15000.00);
        } else if (type.equalsIgnoreCase("Teeth Whitening")) {
            t.setTreatmentId("TRT004");
            t.setTreatmentType("Teeth Whitening");
            t.setTreatmentCost(8500.00);
        } else {
            return null; 
        }
        
        return t;
    }
}