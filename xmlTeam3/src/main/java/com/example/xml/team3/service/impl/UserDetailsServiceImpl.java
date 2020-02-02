package com.example.xml.team3.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			UserPub user = userRepository.getByUsername(username);
			if (user == null) {
				System.out.println("ovde baca izuzetak");
				throw new UsernameNotFoundException("User does not exist.");
			}
			List<GrantedAuthority> userAuthorities = Arrays
					.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
			User userDetails = new User(user.getUsername(), user.getPassword(), userAuthorities);
			return userDetails;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
