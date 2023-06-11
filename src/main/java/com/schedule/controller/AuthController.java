package com.schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.dto.LoginDTO;
import com.schedule.dto.RegisterDTO;
import com.schedule.exception.ApiErrorResponse;
import com.schedule.model.User;
import com.schedule.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<ApiErrorResponse> login(@RequestBody LoginDTO loginDTO) {
		return ResponseEntity.ok(authService.login(loginDTO));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
		return ResponseEntity.ok(authService.register(registerDTO));
	}
	
	@GetMapping("/logout")
	public void logout() {
		System.out.println("before logout: " + SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().toArray(new SimpleGrantedAuthority[0])[0].getAuthority());
		SecurityContextHolder.clearContext();
	}
}
