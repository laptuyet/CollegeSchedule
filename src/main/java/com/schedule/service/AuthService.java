package com.schedule.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schedule.designpattern.UserFactory;
import com.schedule.designpattern.UserType;
import com.schedule.domain.Instructor;
import com.schedule.domain.Student;
import com.schedule.dto.LoginDTO;
import com.schedule.dto.RegisterDTO;
import com.schedule.exception.ApiErrorResponse;
import com.schedule.exception.ExistingResourceException;
import com.schedule.model.Role;
import com.schedule.model.User;
import com.schedule.repo.UserCustomRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;

	private final UserCustomRepo userCustomRepo;

	private final PasswordEncoder passwordEncoder;

	private final InstructorService instructorService;

	private final StudentService studentService;

	public ApiErrorResponse login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.toArray(new SimpleGrantedAuthority[0])[0].getAuthority();

		return ApiErrorResponse.builder().statusCode(200).dateTime(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")))
				.message(role).build();
	}

	public User register(RegisterDTO registerDTO) {

		UserDetails userDetails = userCustomRepo.getUserByUsername(registerDTO.getUsername());

		if (userDetails != null) {
			throw new ExistingResourceException(
					"User with username %s is existed".formatted(registerDTO.getUsername()));
		}

		User newUser;
		switch (registerDTO.getRole()) {
		case ROLE_ACCOUNTANT:
		case ROLE_FACILITY:
		case ROLE_OFFICER:
		case ROLE_LECTURER: {

			newUser = UserFactory.getUser(UserType.INSTRUCTOR);

			newUser.setUsername(registerDTO.getUsername());
			newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
			newUser.setFname(registerDTO.getFname());
			newUser.setLname(registerDTO.getLname());
			newUser.setEmail(registerDTO.getEmail());
			newUser.setPhone(registerDTO.getPhone());
			newUser.setRole(registerDTO.getRole());

			// save user to db
			instructorService.save((Instructor) newUser);
			break;
		}
		default: // case ROLE_STUDENT
			newUser = UserFactory.getUser(UserType.STUDENT);
			newUser.setUsername(registerDTO.getUsername());
			newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
			newUser.setFname(registerDTO.getFname());
			newUser.setLname(registerDTO.getLname());
			newUser.setEmail(registerDTO.getEmail());
			newUser.setPhone(registerDTO.getPhone());
			newUser.setRole(Role.ROLE_STUDENT);

			// save user to db
			studentService.save((Student) newUser);
			break;
		}

		return newUser;
	}
}
