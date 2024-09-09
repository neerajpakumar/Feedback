package com.feedback.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feedback.entity.User;
import com.feedback.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsIpml implements UserDetailsService {

	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User admin = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found at this email : " + username));
		return admin;
	}

}
