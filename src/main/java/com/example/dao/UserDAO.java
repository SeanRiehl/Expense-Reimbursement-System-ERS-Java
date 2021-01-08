package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface UserDAO {
	// CREATE
	public void createUser(String username, String password, String first_name, String last_name, String email, int role_id);

	// READ
	public List<User> readAllUsers();

	// UPDATE

	// DELETE
}
