package com.feedback.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSucessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("custom");
		Set<String> roles= AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println(roles);
		if(roles.contains("ROLE_USER")){
			response.sendRedirect("/user/home");
		}
		else if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/home");
		}
	}

}
