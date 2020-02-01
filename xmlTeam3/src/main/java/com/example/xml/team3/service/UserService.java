package com.example.xml.team3.service;

import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.user.UserRole;

public interface UserService {
	public String registerUser(UserPub newUser, UserRole userRole) throws Exception;

	public UserPub findByUsername(String username);
}
