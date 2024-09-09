package com.feedback.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;

public interface FeedBackRepo extends JpaRepository<Feedback, Integer> {

	Optional<Feedback>findByFeedbackId(Integer id);
	
	Optional<Feedback>findByUser(User user);
	
	@Transactional
	@Modifying
	@Query("Delete From Feedback Where feedbackId=:feedbackId")
	void deleteFeedack(@Param("feedbackId") Integer feedbackId);
	
	List<Feedback> findByRollNumberContaining(String rollNumber);
	
	List<Feedback> findBySubjectContaining(String teacher);

}
