package com.schedule.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schedule.model.User;
import com.schedule.repo.UserCustomRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCustomService implements UserDetailsService{
	
	private final UserCustomRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final User user = userRepo.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Not found user with username: " + username);
		}
		return user;
	}
}
