package com.schedule.dto;

import com.schedule.model.Role;

import lombok.Data;

@Data
public class RegisterDTO {
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private Role role;
}
