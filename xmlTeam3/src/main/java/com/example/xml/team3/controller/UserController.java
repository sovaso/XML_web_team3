package com.example.xml.team3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerAuthor(@RequestBody @Valid RegisterDTO registerDTO) {
		System.out.println("REGISTRATION CALLED");

		UserPub newUser = new UserPub();
		newUser.setEmail(registerDTO.getEmail());
		newUser.setPassword(registerDTO.getPassword());
		newUser.setName(registerDTO.getName());
		newUser.setSurname(registerDTO.getSurname());
		newUser.setUsername(registerDTO.getUsername());
		UserRole role = null;
		if (registerDTO.getRole().equalsIgnoreCase("ROLE_AUTHOR")) {
			role = UserRole.ROLE_AUTHOR;
		} else if (registerDTO.getRole().equalsIgnoreCase("ROLE_REVIEWER")) {
			role = UserRole.ROLE_REVIEWER;
		} else {
			role = UserRole.ROLE_EDITOR;
		}
		try {
			userService.registerUser(newUser, role);
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/getAllReviewers")
	public ResponseEntity<List<RegisterDTO>> getAllReviewers() {
		System.out.println("========Uslo u get all reviewers=========");
	
		List<RegisterDTO> retVal = new ArrayList<>();
		String usernameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<UserPub> allForReviewing = userService.getAllReviewers(usernameCurrentUser);
		for (UserPub userPub : allForReviewing) {
			RegisterDTO r = new RegisterDTO();
			r.setEmail(userPub.getEmail());
			r.setName(userPub.getName());
			r.setUsername(userPub.getUsername());
			r.setSurname(userPub.getSurname());
			r.setRole("ROLE_REVIEWER");
			retVal.add(r);
		}
		System.out.println("====== "+retVal.size());
		return new ResponseEntity<List<RegisterDTO>>(retVal, HttpStatus.OK);
	}
}
