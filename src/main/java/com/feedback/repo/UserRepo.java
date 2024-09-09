package com.feedback.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.Feedback;
import com.feedback.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username) ;
	
	Optional<User> findByFeedback(Feedback feedabck) ;
	
	Optional<User> findByUserId(Integer id);
	
	
	

}
