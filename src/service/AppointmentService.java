/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AppointmentDAO;
import dao.PatientDAO;
import model.Appointment;
import model.Patient;

/**
 *
 * @author ilpeiris
 */
public class AppointmentService {
    private PatientDAO patientDAO;
    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.patientDAO = new PatientDAO();
        this.appointmentDAO = new AppointmentDAO();
    }

    public boolean processRegistration(Patient patient, Appointment appointment) {
        //// if patient doesnt exist in DB, register them first (<<extend>> logic)
        if (!patientDAO.patientExists(patient.getContactNumber())) {
            patientDAO.addPatient(patient);
        }
        
        return appointmentDAO.addAppointment(appointment);
    }
}