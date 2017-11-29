package com.books.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInOutController implements AuthenticationSuccessHandler {

	@RequestMapping("/logout")
	public String setupForm(Map<String, Object> map, HttpServletRequest request) {
		request.getSession(true).invalidate();
		return "redirect:/login";
	}

	@Override
	@RequestMapping("/")
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		System.out.println(role);
		if (role.contains("ADMIN")) {
			response.sendRedirect(response.encodeURL("admin"));
		} else {
			if (role.contains("USER"))
				response.sendRedirect(response.encodeURL("user"));
			else
				response.sendRedirect(response.encodeURL("login"));
		}

	}
}
