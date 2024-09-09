package com.feedback.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer feedbackId;
	    private String Strengths;
	    private String Weakness;
	    private String Subject;
	    private String rating;
	    private String Feedback;
	    private LocalDate date;
	    
	    @ManyToOne
	    private User user;
	}

