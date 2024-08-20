package com.jwt.JWT.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo")
	public ResponseEntity<String> demo(){
		return ResponseEntity.ok("Hello to jwt authorization");
	}
}
