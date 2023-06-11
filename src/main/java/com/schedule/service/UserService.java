package com.schedule.service;

import com.schedule.model.User;

public interface UserService {
	
	User findByUsername(String username);
}
