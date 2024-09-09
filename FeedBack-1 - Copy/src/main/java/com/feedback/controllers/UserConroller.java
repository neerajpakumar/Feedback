package com.feedback.controllers;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feedback.entity.User;
import com.feedback.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feed")
public class UserConroller {
	
	private final UserRepo repo;
	private final PasswordEncoder encoder;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/reg")
	public String register() {
		return "register";
	}
	
	
	@PostMapping("/doreg")
	public String doReg(@ModelAttribute User user) {
		user.setRoleList(List.of("ROLE_USER"));
		user.setPassword(encoder.encode(user.getPassword()));
		this.repo.save(user);
		return "redirect:/feed/login";
	}


}
