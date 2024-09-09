package com.feedback.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;
import com.feedback.repo.FeedBackRepo;
import com.feedback.repo.UserRepo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FeedController {

	private final FeedBackRepo feedBackRepo;

	private final UserRepo repo;

	public String getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		System.out.println(name);
		return name;
	}

	@GetMapping("/home")
	public String getDashboard(Model model) {
//	        List<User> user = this.repo.findAll(); 
		List<Feedback> user = this.feedBackRepo.findAll();
		user.forEach(c -> {
			System.out.println(c.getFeedback());
		});
		model.addAttribute("feed", user);
		return "Admin/home";
	}

	@GetMapping("/getFeedback")
	public String getFeedback(Model model,@RequestParam String name,@RequestParam String seacrh) {

		if (seacrh.equals("roolNumber")) {
			List<Feedback> findByRollNumber = this.feedBackRepo.findByRollNumberContaining(name);
			model.addAttribute("feed", findByRollNumber);
		} else if (seacrh.equals("subject")) {
			List<Feedback> findByRollNumber = this.feedBackRepo.findBySubjectContaining(name);
			model.addAttribute("feed", findByRollNumber);
		} else {
			return "Admin/home";
		}
		return "Admin/home";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		User user = this.repo.findByEmail(getUser()).orElse(null);
		model.addAttribute("feed", user);
		return "Admin/seeProfile";
	}

	@GetMapping("/allUser")
	public String allUser(Model model) {
		System.out.println("ji");
		List<User> user = this.repo.findAll();
		model.addAttribute("feed", user);
		System.out.println("kaho");
		return "Admin/userView";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, HttpSession httpSession) {
//	    	Feedback feedback = this.feedBackRepo.deleteFeedack(id).orElseThrow();
		this.feedBackRepo.deleteFeedack(id);
		return "redirect:/admin/home";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model) {
		Feedback feedback = this.feedBackRepo.findByFeedbackId(id).orElseThrow();
		model.addAttribute("feed", feedback);
		return "Admin/update";
	}

	@PostMapping("/do_update")
	public String do_update(@ModelAttribute Feedback feedback, HttpSession httpSession) {
		feedback.setDate(LocalDate.now());
		this.feedBackRepo.save(feedback);
		return "redirect:/admin/home";
	}

}
