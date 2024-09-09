package com.feedback.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.Feedback;

public interface FeedBackRepo extends JpaRepository<Feedback, Integer> {

	Optional<Feedback>findByFeedbackId(Integer id);
	   

}
