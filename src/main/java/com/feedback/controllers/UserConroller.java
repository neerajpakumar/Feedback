package com.feedback.controllers;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feedback.entity.User;
import com.feedback.repo.UserRepo;

import jakarta.servlet.http.HttpSession;
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
	
	@GetMapping("/homes")
	public String homes() {
		return "homes";
	}
	
	@GetMapping("/reg")
	public String register() {
		return "register";
	}
	
	
	@PostMapping("/doreg")
	public String doReg(@ModelAttribute User user,HttpSession session,Model model) {
		User user2 = this.repo.findByEmail(user.getEmail()).orElse(null);
		if(user2==null) {
			user.setRoleList(List.of("ROLE_USER"));
			user.setPassword(encoder.encode(user.getPassword()));
			this.repo.save(user);
			session.setAttribute("msg", "Registration SucessFull");
			return "redirect:/feed/home";
		}
		else {
			model.addAttribute("user",user);
			session.setAttribute("msg", "Email Alredy Exist try with other email");
			return "redirect:/feed/reg";
		}
		
	}


}
