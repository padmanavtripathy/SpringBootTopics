package com.jwt.JWT.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/managment")
@PreAuthorize("hasRole('ADMIN')")
public class Admin {
	
	@GetMapping
	public String checkAdmin() {
		return "hidden url";
	}
}
