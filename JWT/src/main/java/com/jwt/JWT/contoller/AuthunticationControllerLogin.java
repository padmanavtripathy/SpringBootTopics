package com.jwt.JWT.contoller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JWT.Model.Login;
import com.jwt.JWT.Model.User;
import com.jwt.JWT.Model.response.AuthenticationResponse;
import com.jwt.JWT.service.Authentication;

import io.jsonwebtoken.security.Request;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthunticationControllerLogin {

	private Authentication authentication;

	public AuthunticationControllerLogin(Authentication authentication) {
		super();
		this.authentication = authentication;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
		return ResponseEntity.ok(authentication.register(user));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody Login login) {
		return ResponseEntity.ok(authentication.login(login));
	}

	@GetMapping("/")
	public String testClass() {
		return "welcome to jwt Authorization";
	}

	@GetMapping("/userDetails")
	public String userDetails(HttpServletRequest request) {
		return "validated";
	}
}