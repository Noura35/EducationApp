package org.example.educationapp.web;


import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Payment;
import org.example.educationapp.entities.Student;
import org.example.educationapp.repository.PaymentRepository;
import org.example.educationapp.repository.StudentRepository;
import org.example.educationapp.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {

    private PaymentService paymentService;
    private StudentRepository strepo;
    private PaymentRepository payrepo;

    public PaymentRestController(StudentRepository strepo, PaymentRepository payrepo,PaymentService paymentService) {
        this.strepo = strepo;
        this.payrepo = payrepo;
        this.paymentService = paymentService;

    }


    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return payrepo.findAll();
    }

    @GetMapping("/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code ) {
        return payrepo.findByStudiantCode(code);
    }

    @GetMapping("/payments/bystatus")
    public List<Payment> paymentsByStatus(@RequestParam PayementStatus status ) {
        return payrepo.findByStatus(status);
    }


    @GetMapping("/payments/bytype")
    public List<Payment> paymentsByType(@RequestParam PayementType type ) {
        return payrepo.findByType(type);
    }




    @GetMapping("/payments/{id}")
    public Payment findPaymentById(@PathVariable long id) {
        return payrepo.findById(id).get();
    }

    /*


    @PutMapping("/")
    public Payment updatePayment(@RequestBody Payment payment) {
        return payrepo.save(payment);
    }

*/


    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PayementStatus status,@PathVariable  Long id) {

        Payment payment = payrepo.findById(id).get();
        payment.setStatus(status);
        return payrepo.save(payment);

    }


    @GetMapping("/students")
    public List<Student> allStudents() {
        return strepo.findAll();
    }


    @GetMapping("/students/{code}")
    public Student findStudentByCode(@PathVariable String code) {
        return strepo.findByCode(code);
    }


    @GetMapping("/studentsByProgramId")
    public List<Student> allStudentsByProgramId(@RequestParam String programId) {
        return strepo.findByProgramId(programId);
    }


    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date,
                               double amount, PayementType type,String studentCode) throws IOException {

        return paymentService.savePayment(file,date,amount,type,studentCode);
    }



    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPayementFile(@PathVariable  Long paymentId) throws IOException {
        Payment payment = payrepo.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
}


}
