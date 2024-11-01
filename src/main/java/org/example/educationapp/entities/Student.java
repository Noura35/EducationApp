package org.example.educationapp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter @ToString

public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String code;
    private String programId;
    private String photo;




}
