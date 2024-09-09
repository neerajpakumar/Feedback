package com.feedback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feedback.entity.Feedback;
import com.feedback.repo.FeedBackRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FeedController {

    private final FeedBackRepo feedBackRepo;

	
	public String getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		System.out.println(name);
		return name;
	}


	    @GetMapping("/home")
	    public String getDashboard(Model model) {
	        List<Feedback> findAll = this.feedBackRepo.findAll();
	        model.addAttribute("all", findAll);
	        findAll.forEach(s->{
	        	System.out.println(s.getFeedback());
	        });
	        return "Admin/home";
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String delete(@PathVariable Integer id) {
	    	Feedback feedback = this.feedBackRepo.findByFeedbackId(id).orElseThrow();
	    	this.feedBackRepo.delete(feedback);
	    	return "redirect:/admin/home";
	    }
	    
	
	
}
