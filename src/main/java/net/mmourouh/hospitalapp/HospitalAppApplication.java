package net.mmourouh.hospitalapp;

import net.mmourouh.hospitalapp.entities.Patient;
import net.mmourouh.hospitalapp.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
                List<String> patientsNames = List.of(
                        "Ahmed", "Mohamed", "Fatima", "Hassan", "Aicha", "Omar", "Nadia", "Khalid", "Karim", "Sara",
                        "Nora", "Youssef", "Samira", "Mehdi", "Hanae", "Rachid", "Nawal", "Abdellah", "Naima", "Younes"
                );
            patientsNames.forEach(name -> {
                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setBirthdate(new Date());
                    patient.setSick(Math.random() > 0.5);
                    patient.setScore((int) (Math.random() * 100 + 10));
                    patientRepository.save(patient);
                });
        };
    }
}
