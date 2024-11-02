package org.example.educationapp;

import org.example.educationapp.entities.PayementStatus;
import org.example.educationapp.entities.PayementType;
import org.example.educationapp.entities.Payment;
import org.example.educationapp.entities.Student;
import org.example.educationapp.repository.PaymentRepository;
import org.example.educationapp.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EducationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationAppApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(StudentRepository stdrepo, PaymentRepository payrepo) {
        return args -> {
            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                            .firstName("noura").lastName("bha").code("123a").programId("ing").build());
            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                    .firstName("abir").lastName("bha").code("123k").programId("ing").build());


            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                    .firstName("roua").lastName("bha").code("123b").programId("ing").build());

            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                    .firstName("nawres").lastName("bha").code("123c").programId("ing").build());


            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                    .firstName("hana").lastName("bha").code("123d").programId("ing").build());


            stdrepo.save(new Student().builder().id(UUID.randomUUID().toString())
                    .firstName("amal").lastName("bha").code("123e").programId("ing").build());


            PayementType[] payementType=PayementType.values();
            Random random = new Random();

            stdrepo.findAll().forEach(st->{
                for (int i=0;i<10;i++){
                    int index=random.nextInt(payementType.length);
                    Payment p=Payment.builder()
                            .amount(1000*(int)(Math.random()+2000))
                            .type(payementType[index])
                            .status(PayementStatus.CREATED)
                            .date(LocalDate.now())
                            .studiant(st)
                            .build();

                    payrepo.save(p);
                }
            });
        };
    }



}
