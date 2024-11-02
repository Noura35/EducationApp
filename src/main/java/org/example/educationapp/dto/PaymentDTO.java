package org.example.educationapp.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Student;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter @ToString

public class PaymentDTO {

    private Long id;
    private LocalDate date;
    private double amount;
    private PayementType type;
    private PayementStatus status;
}
