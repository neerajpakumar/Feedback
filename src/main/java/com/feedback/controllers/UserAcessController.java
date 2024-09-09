package com.feedback.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.repo.FeedBackRepo;
import com.feedback.repo.UserRepo;

import jakarta.servlet.http.HttpSession;
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
	public String home(Model model) {
		User user = this.repo.findByEmail(getUser()).orElse(null);
		Feedback feedback = this.backRepo.findByUser(user).orElse(null);
		if(feedback==null) {
			return "User/home";
		}
		else {
			return "User/feedback";
		}
	}
	
	@GetMapping("/about")
	public String about() {
		return "User/about";
	}
	
	@GetMapping("/profile")
	public String profile(Model model) {
		User user = this.repo.findByEmail(getUser()).orElse(null);
		if (user !=null) {
			model.addAttribute("user",user);
			return "User/profile";
		}
		
		return "User/profile";
	}
	
	@GetMapping("/services")
	public String services() {
		return "User/services";
	}
    @PostMapping("/addFeedback")
    public String addFeedback(@ModelAttribute  Feedback feedback,HttpSession session,Model model) {
    	feedback.setFeedback(feedback.getFeedback());
    	User user = this.repo.findByEmail(getUser()).orElseThrow();
    	feedback.setDate(LocalDate.now());
    	feedback.setUser(user);
    	this.backRepo.save(feedback);
    	session.setAttribute("msg","Submited Sucessfully");
    	return "redirect:/user/home";
    }
	
}
