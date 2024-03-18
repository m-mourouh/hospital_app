package net.mmourouh.hospitalapp.services;

import jakarta.transaction.Transactional;
import net.mmourouh.hospitalapp.entities.Appointment;
import net.mmourouh.hospitalapp.entities.Consultation;
import net.mmourouh.hospitalapp.entities.Doctor;
import net.mmourouh.hospitalapp.entities.Patient;
import net.mmourouh.hospitalapp.repositories.AppointmentRepository;
import net.mmourouh.hospitalapp.repositories.ConsultationRepository;
import net.mmourouh.hospitalapp.repositories.DoctorRepository;
import net.mmourouh.hospitalapp.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    PatientRepository patientRepository;
    DoctorRepository doctorRepository;
    AppointmentRepository appointmentRepository;
    ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.consultationRepository = consultationRepository;
    }



    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        appointment.setId(UUID.randomUUID().toString());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepository.save(consultation);
    }
}
