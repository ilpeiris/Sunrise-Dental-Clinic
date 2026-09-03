/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test_case;
import pattern.TreatmentFactory;
import model.Treatment;
/**
 *
 * @author ilpeiris
 */
public class SunriseDentalTestRunner {

    static int totalTests = 0;
    static int passedTests = 0;

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   SUNRISE DENTAL: AUTOMATED TESTING  ");
        System.out.println("======================================");

        // Run Test 1: General Cleaning Cost
        Treatment t1 = TreatmentFactory.getTreatment("General Cleaning");
        runTest(
                "Valid Treatment Cost (General Cleaning)",
                String.valueOf(t1 != null ? t1.getTreatmentCost() : "null"),
                "2500.0"
        );

        // Run Test 2: Root Canal Cost
        Treatment t2 = TreatmentFactory.getTreatment("Root Canal");
        runTest(
                "Valid Treatment Cost (Root Canal)",
                String.valueOf(t2 != null ? t2.getTreatmentCost() : "null"),
                "15000.0"
        );

        // Run Test 3: Invalid Treatment Type
        Treatment t3 = TreatmentFactory.getTreatment("Heart Surgery");
        runTest(
                "Invalid Treatment Selection",
                t3 == null ? "null" : "Object Created",
                "null"
        );

        // Run Test 4: Teeth Whitening ID Check
        Treatment t4 = TreatmentFactory.getTreatment("Teeth Whitening");
        runTest(
                "Verify Treatment ID Generation",
                t4 != null ? t4.getTreatmentId() : "null",
                "TRT004"
        );

        // Print Final Summary
        System.out.println();
        System.out.println("======================================");
        System.out.println("              TEST SUMMARY            ");
        System.out.println("======================================");
        System.out.println("Total Tests  : " + totalTests);
        System.out.println("Passed Tests : " + passedTests);
        System.out.println("Failed Tests : " + (totalTests - passedTests));
        System.out.println("======================================");
    }

    public static void runTest(String testName, String actual, String expected) {
        totalTests++;
        System.out.println();
        System.out.println("Test Case : " + testName);
        System.out.println("Expected  : " + expected);
        System.out.println("Actual    : " + actual);

        if (actual.equals(expected)) {
            passedTests++;
            System.out.println("Result    : PASS");
        } else {
            System.out.println("Result    : FAIL");
        }
    }
}