package com.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedule.utils.AppConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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


    @NotEmpty(message = "First name should not be empty")
    @NotBlank(message = "First name should not be blank")
    private String fname;

    @NotEmpty(message = "Last name should not be empty")
    @NotBlank(message = "Last name should not be blank")
    private String lname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private Boolean gender; // 0: male, 1: female

    @NotEmpty(message = "Address should not be empty")
    @NotBlank(message = "Address should not be blank")
    private String address;

    @NotEmpty(message = "Email should not be empty")
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Invalid email pattern",
            regexp = AppConstants.EMAIL_REGEX_PATTERN)
    private String email;

    @NotEmpty(message = "Phone should not be empty")
    @NotBlank(message = "Phone should not be blank")
    @Pattern(message = "Invalid phone pattern",
            regexp = AppConstants.PHONE_REGEX_PATTERN)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotEmpty(message = "Username should not be empty")
    @NotBlank(message = "Username should not be blank")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password should not be blank")
    private String password;
}
