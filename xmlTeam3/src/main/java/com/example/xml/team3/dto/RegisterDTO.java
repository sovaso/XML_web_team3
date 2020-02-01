package com.example.xml.team3.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class RegisterDTO {
	protected String name;
	protected String surname;
	protected String username;
	protected String password;
	protected String email;
	protected String role;
	protected Boolean enabled;
	protected Set<GrantedAuthority> authorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public RegisterDTO(String name, String surname, String username, String password, String email, String role,
			Boolean enabled, Set<GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public RegisterDTO() {
		super();
	}

}
