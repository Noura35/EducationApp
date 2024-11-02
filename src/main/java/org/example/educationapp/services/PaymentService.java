package org.example.educationapp.services;


import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Payment;
import org.example.educationapp.entities.Student;
import org.example.educationapp.repository.PaymentRepository;
import org.example.educationapp.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {

    private StudentRepository strepo;
    private PaymentRepository payrepo;

    public PaymentService(StudentRepository strepo, PaymentRepository payrepo) {
        this.strepo = strepo;
        this.payrepo = payrepo;
    }

    public Payment savePayment( MultipartFile file, LocalDate date,
                               double amount, PayementType type, String studentCode) throws IOException {


        //emplacement to save file : create fil home mte3 user nouveau dossier esmou "app-data" w fih dossier "payments"
        Path folderPath = Paths.get(System.getProperty("user.home"),"app-data","payments");
        if(!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"app-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(), filePath);


        Student student = strepo.findByCode(studentCode);

        Payment payment=Payment.builder()
                .date(date)
                .amount(amount)
                .type(type)
                .file(filePath.toUri().toString())
                .studiant(student)
                .status(PayementStatus.CREATED)
                .build();

        return payrepo.save(payment);
    }







}
