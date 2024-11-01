package org.example.educationapp.web;


import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Payment;
import org.example.educationapp.entities.Student;
import org.example.educationapp.repository.PaymentRepository;
import org.example.educationapp.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentRestController {

    private StudentRepository strepo;
    private PaymentRepository payrepo;

    public PaymentRestController(StudentRepository strepo, PaymentRepository payrepo) {
        this.strepo = strepo;
        this.payrepo = payrepo;
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
    @PostMapping("/")
    public Payment addPayment(@RequestBody Payment payment) {
        return payrepo.save(payment);
    }

    @PutMapping("/")
    public Payment updatePayment(@RequestBody Payment payment) {
        return payrepo.save(payment);
    }

*/
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




}
