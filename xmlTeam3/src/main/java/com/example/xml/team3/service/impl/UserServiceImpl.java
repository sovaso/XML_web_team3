package com.example.xml.team3.service.impl;

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
			e.printStackTrace();
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
			throw new Exception("Given email already exists.");
		}

	}
}
