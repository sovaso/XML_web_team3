package com.example.xml.team3.service;

import java.util.List;

import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.user.UserRole;

public interface UserService {
	public String registerUser(UserPub newUser, UserRole userRole) throws Exception;

	public UserPub findByUsername(String username);
	
	public List<UserPub> getAllReviewers(String editorUsername);
	
	public String getEmailByUsername(String username);
}
