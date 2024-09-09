package com.feedback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Securtiy {
	
	private final CustomSucessHandler sucessHandler;
	
	private final UserDetailsService userDetailsService;
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(encoder());
		return authenticationProvider;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
			auth.requestMatchers("/user/**").hasRole("USER");
			auth.requestMatchers("/feed/**").permitAll();
		});

		httpSecurity.formLogin(fromLogin -> {
			fromLogin.loginPage("/feed/home");
			fromLogin.usernameParameter("email").passwordParameter("password");
			fromLogin.loginProcessingUrl("/login");
			fromLogin.successHandler(sucessHandler);
		});
		
//		httpSecurity.httpBasic(Customizer.withDefaults());
//		httpSecurity.formLogin(Customizer.withDefaults());
//		httpSecurity.oauth2Login(Customizer.withDefaults());

		return httpSecurity.build();
	}
}
