package com.schedule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fname;

    private String lname;

    private LocalDate dob;

    private Boolean gender; // 0: male, 1: female

    private String address;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;

    private String password;
}
