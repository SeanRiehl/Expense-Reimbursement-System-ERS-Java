package com.example.service;

import java.util.List;

import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;
import com.example.model.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO = new UserDAOImpl();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("THE STATIC BLOCK HAS FAILED");
			e.printStackTrace();
		}
	}

	@Override
	public int verifyCredentials(String username, String password) {
		// Get the user list
		List<User> userList = userDAO.readAllUsers();

		// Find a matching user in the table
		// Return 0 for employees, 1 for financial managers, -1 otherwise
		for (User u : userList) {
			if (u.getUsername().equals(username) & u.getPassword().equals(password)) {
				if (u.getRole_id() == 0) {
					return 0;
				} else if (u.getRole_id() == 1) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

	@Override
	public List<User> listAllUsers() {
		// Get the user list
		List<User> userList = userDAO.readAllUsers();

		return userList;
	}
}
