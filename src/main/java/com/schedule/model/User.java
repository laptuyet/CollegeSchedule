package com.schedule.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schedule.utils.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.getRole().name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
