package com.feedback.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username) ;

}
