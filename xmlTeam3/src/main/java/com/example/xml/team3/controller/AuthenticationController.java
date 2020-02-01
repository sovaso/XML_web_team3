package com.example.xml.team3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.LoginRequestDTO;
import com.example.xml.team3.dto.LoginResponseDTO;
import com.example.xml.team3.util.jwt.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtils;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
		System.out.println("Uslo u login");
		try {
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Create JSON web token for user
			User user = (User) authentication.getPrincipal();
			String jwt = jwtUtils.generateToken(user);

			// Return token for successful authentication
			LoginResponseDTO response = new LoginResponseDTO(jwt);
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<String>("Invalid email or password", HttpStatus.BAD_REQUEST);
		}
	}

}
