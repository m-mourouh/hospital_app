package net.mmourouh.hospitalapp.repositories;

import net.mmourouh.hospitalapp.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
