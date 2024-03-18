package net.mmourouh.hospitalapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Appointment {
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToOne(mappedBy = "appointment")
    private Consultation consultation;
}
