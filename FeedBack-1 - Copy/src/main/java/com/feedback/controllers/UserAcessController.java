package com.feedback.controllers;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.repo.FeedBackRepo;
import com.feedback.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAcessController {

	private final FeedBackRepo backRepo;
	private final UserRepo repo;
	public String getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		System.out.println(name);
		return name;
	}
	@GetMapping("/home")
	public String home() {
		return "User/home";
	}
	
    @PostMapping("/addFeedback")
    public String addFeedback(@ModelAttribute  Feedback feedback) {
    	System.out.println("hi");
    	feedback.setFeedback(feedback.getFeedback());
    	User user = this.repo.findByEmail(getUser()).orElseThrow();
    	feedback.setDate(LocalDate.now());
    	feedback.setUser(user);
    	this.backRepo.save(feedback);
    	return "redirect:/user/home";
    }
	
}
