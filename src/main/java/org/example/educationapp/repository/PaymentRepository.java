package org.example.educationapp.repository;

import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudiantCode(String code);
    List<Payment> findByStatus(PayementStatus status);
    List<Payment> findByType(PayementType status);


}
