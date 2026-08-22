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

        int patientDbId = patientDAO.getPatientIdByContact(patient.getContactNumber());
        

        if (patientDbId == -1) {
            patientDbId = patientDAO.addPatientAndGetId(patient);
        }
        
      
        appointment.setPatientId(patientDbId);
        
     
        return appointmentDAO.addAppointment(appointment);
    }
}