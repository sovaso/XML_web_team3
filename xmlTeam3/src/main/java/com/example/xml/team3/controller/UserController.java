package com.example.xml.team3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.RegisterDTO;
import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.user.UserRole;
import com.example.xml.team3.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerAuthor(@RequestBody @Valid RegisterDTO registerDTO) {
		UserPub newUser = new UserPub();
		newUser.setEmail(registerDTO.getEmail());
		newUser.setPassword(registerDTO.getPassword());
		newUser.setName(registerDTO.getName());
		newUser.setSurname(registerDTO.getSurname());
		newUser.setUsername(registerDTO.getUsername());
		UserRole role = null;
		if (registerDTO.getRole().equalsIgnoreCase("author")) {
			role = UserRole.ROLE_AUTHOR;
		} else if (registerDTO.getRole().equalsIgnoreCase("reviewer")) {
			role = UserRole.REVIEWER;
		} else {
			role = UserRole.EDITOR;
		}
		try {
			String username = userService.registerUser(newUser, role);
			return new ResponseEntity<String>("User with " + username + " has been succesfully registered!",
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("An error occured during registration. Please try again later.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
