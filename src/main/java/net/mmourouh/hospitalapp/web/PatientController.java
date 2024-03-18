package net.mmourouh.hospitalapp.web;


import net.mmourouh.hospitalapp.entities.Patient;
import net.mmourouh.hospitalapp.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<Patient> patients() {
        return this.patientRepository.findAll();
    }
}
