package com.example.xml.team3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.user.UserRole;
import com.example.xml.team3.repository.UserRepository;
import com.example.xml.team3.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static String emailRegex = "[^@]+@[^\\.]+\\..+";

	public String registerUser(UserPub newUser, UserRole userRole) throws Exception {
		try {
			validateUserData(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Given username already exists.");
		}
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setRole(userRole);
		return userRepository.saveUser(newUser);
	}

	public UserPub findByUsername(String username) {
		return this.userRepository.getByUsername(username);
	}

	private void validateUserData(UserPub user) throws Exception {

		if (!user.getEmail().matches(emailRegex)) {
			throw new Exception("Email is invalid.");
		}

		if (userRepository.getByUsername(user.getUsername()) != null) {
			System.out.println("VALIDATE USER DATA: USERNAME ALREADY EXIST");
			throw new Exception("Given username already exists.");
		}

	}
	
	public List<UserPub> getAllReviewers(String editorUsername){
		return userRepository.getAllReviewers(editorUsername);
	}
}
