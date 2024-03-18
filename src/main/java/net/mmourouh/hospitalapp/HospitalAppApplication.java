package net.mmourouh.hospitalapp;

import net.mmourouh.hospitalapp.entities.*;
import net.mmourouh.hospitalapp.repositories.AppointmentRepository;
import net.mmourouh.hospitalapp.repositories.ConsultationRepository;
import net.mmourouh.hospitalapp.repositories.DoctorRepository;
import net.mmourouh.hospitalapp.repositories.PatientRepository;
import net.mmourouh.hospitalapp.services.HospitalServiceImpl;
import net.mmourouh.hospitalapp.services.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository,
                                   DoctorRepository doctorRepository,
                                   AppointmentRepository appointmentRepository,
                                   ConsultationRepository consultationRepository,
                                   HospitalServiceImpl hospitalService,
                                   UserServiceImpl userService) {
        return args -> {

            //Add some patients to the database
            Stream.of("Mariam", "Rida", "Albert", "Alexandre", "Cecile").forEach(name -> {
                Patient patient = Patient.builder()
                        .name(name)
                        .birthdate(new Date())
                        .sick(Math.random() > 0.5)
                        .score((int) (Math.random() * 20))
                        .build();
                //hospitalService.savePatient(patient);
            });
            //=====================Operations on Patient==========================================
            //Display all patients
            System.out.println("=====================Find All patients=========================");
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });
            //Find a patient by id
            System.out.println("====================Find a patient by id:==================");
            Patient patient = patientRepository.findById(1L).get();
            System.out.println("Name = " + patient.getName());
            System.out.println("Score = " + patient.getScore());
            System.out.println("Sick = " + patient.isSick());
            System.out.println("Birth Date = " + patient.getBirthdate());
            //find patients where sick = true
            System.out.println("====================Find patients where sick = true:==================");
            patientRepository.findBySick(true).forEach(p -> {
                System.out.println(p.toString());
            });
            //find patients where name contains "M"
            System.out.println("====================Find patients where name contains 'm':==================");
            patientRepository.findByNameContains("m").forEach(p -> System.out.println(p.toString()));

            System.out.println("====================Find patients where name contains 'a':==================");
            patientRepository.search("%a%").forEach(p -> System.out.println(p.toString()));

            System.out.println("====================Find patients where score is greater that 5 :==================");
            patientRepository.findByScoreGreaterThan(5).forEach(p -> System.out.println(p.toString()));


            // update a patient
            System.out.println("====================Update a patient:==================");
            Patient patient1 = patientRepository.findById(1L).get();
            patient1.setScore(20);
            patient1.setSick(true);
            patientRepository.save(patient1);
            System.out.println(patient1.toString());
            //Delete a patient
            System.out.println("====================Delete a patient:==========================");
            patientRepository.deleteById(3L);

            System.out.println("=====================New patients List=========================");
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });

            //Add some doctors to the database
            Stream.of("Ahmed", "Mark", "John", "Sami").forEach(name -> {
                Doctor doctor =  Doctor.builder()
                        .name(name)
                        .email(name + "@gmail.com")
                        .specialty(Math.random() > 0.5 ? "Dentist" : "Cardiologist")
                        .build();

                //hospitalService.saveDoctor(doctor);
            });


            //Find patient by name|id
            Patient p1 = patientRepository.findByName("Mariam");
            Patient p2 = patientRepository.findById(1L).orElse(null);

            //Find doctor by name
            Doctor doctor = doctorRepository.findByName("Ahmed");

            //Build an appointment

            Appointment appointment = Appointment.builder()
                    .date(new Date())
                    .status(AppointmentStatus.PENDING)
                    .patient(p1)
                    .doctor(doctor)
                    .build();

            //hospitalService.saveAppointment(appointment);


            //Build a consultation
            Consultation consultation = Consultation.builder()
                    .consultationDate(new Date())
                    .report("Patient is doing well")
                    .appointment(appointmentRepository.findAll().get(0))
                    .build();

            //hospitalService.saveConsultation(consultation);

            //Add some users to the database
            Stream.of("admin", "user").forEach(role -> {
                User user = User.builder()
                        .username(role)
                        .password("1234")
                        .build();
                //userService.addNewUser(user);
            });
            // Add some roles to the database
            Stream.of("STUDENT", "ADMIN", "USER").forEach(r -> {
                Role role = Role.builder()
                        .roleName(r)
                        .build();
                //userService.addNewRole(role);
            });
            // Add roles to users
            //userService.addRoleToUser("admin", "ADMIN");
            //userService.addRoleToUser("admin", "USER");

            //userService.addRoleToUser("user", "STUDENT");
            //userService.addRoleToUser("user", "USER");

            //Authenticate a user
            System.out.println("================================User Authentication=========================");
            try {
                User user = userService.authenticate("user", "1234");
                //show user attributes except password
                System.out.println("User authenticated successfully! ✅");
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Roles: " + user.getRoles().stream().map(Role::getRoleName).toList());

            } catch (Exception e) {
                System.out.println("User not authenticated! ❌");
                e.printStackTrace();
            }
        };
    }
}
