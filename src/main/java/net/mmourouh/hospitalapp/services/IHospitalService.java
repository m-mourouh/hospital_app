package net.mmourouh.hospitalapp.services;

import net.mmourouh.hospitalapp.entities.Appointment;
import net.mmourouh.hospitalapp.entities.Consultation;
import net.mmourouh.hospitalapp.entities.Doctor;
import net.mmourouh.hospitalapp.entities.Patient;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Doctor saveDoctor(Doctor doctor);

    Appointment saveAppointment(Appointment appointment);
    Consultation saveConsultation(Consultation consultation);
}
