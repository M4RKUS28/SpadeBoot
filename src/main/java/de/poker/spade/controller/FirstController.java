package de.poker.spade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstController {

	@GetMapping("/welcome")
	public ResponseEntity<?> sayHello(Authentication auth) {
		if(auth != null)
			return new ResponseEntity<String>("Dear " + auth.getName() + ", welcome!", HttpStatus.OK);
				
		return new ResponseEntity<String>("Welcome guest!", HttpStatus.OK);
	}

}

