package org.example.educationapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity

@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter @ToString

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PayementType type;
    private PayementStatus status;
    private String file;

    @ManyToOne
    private Student studiant;


}
