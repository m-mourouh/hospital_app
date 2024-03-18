package net.mmourouh.hospitalapp.repositories;

import net.mmourouh.hospitalapp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
