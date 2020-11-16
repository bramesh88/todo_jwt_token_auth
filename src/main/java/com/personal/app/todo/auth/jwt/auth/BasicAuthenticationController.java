package com.personal.app.todo.auth.jwt.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class BasicAuthenticationController {

	@GetMapping("/basicauth")
	public AuthenticationBean authenticate() {
		return new AuthenticationBean("You are authenticated..");
	}
}
