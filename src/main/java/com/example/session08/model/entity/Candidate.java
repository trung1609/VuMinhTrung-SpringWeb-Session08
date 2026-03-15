package com.example.session08.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Positive(message = "Age must be greater than 0")
    private Integer age;

    @Column(nullable = false)
    @PositiveOrZero(message = "Years of experience must be 0 or greater")
    private Integer yearsOfExperience;

    private String address;
    private String phone;
    private String bio;
    private String avatar;
}
