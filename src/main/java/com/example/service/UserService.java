package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	// public boolean accountCreation();
	public int verifyCredentials(String username, String password);
	public List<User> listAllUsers();
}
