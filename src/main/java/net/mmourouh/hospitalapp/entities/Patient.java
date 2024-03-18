package net.mmourouh.hospitalapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
//@Table(name = "PATIENTS")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private boolean sick;
    private int score;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Appointment> appointment;
}