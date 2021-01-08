package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.example.model.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

public class LogInController {
	public static String login(HttpServletRequest req, HttpServletResponse resp) {
		UserService userService = new UserServiceImpl();
//		System.out.println("IN LOG IN CONTROLLER");

		// Confirm the user reached this point via a POST
		if (!req.getMethod().equals("POST")) {
//			System.out.println("REQUEST IS NOT A POST TYPE");
			return "/index.html";
		}

		// Extract the form data
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// Verify the user credentials
		int roleID = userService.verifyCredentials(username, password);

		// Redirect to the correct page
		if (roleID == 0) { // Employee
//			System.out.println("USER IS AN EMPLOYEE");

			// Save the employee's user ID
			int author = -1;
			List<User> userList = userService.listAllUsers();
			for (User u : userList) {
				if (u.getUsername().equals(username)) {
					author = u.getUsers_id();
				}
			}

			// Save the variables to the session
			req.getSession().setAttribute("loggedUsername", username); // .getAttribute can get these values
			req.getSession().setAttribute("loggedAuthor", author);

			final Logger loggy = Logger.getLogger(LogInController.class);
			loggy.setLevel(Level.ALL);
			loggy.info("INFO: User logged in: Username: " + username + ".");

			return "/forwarding/employeeHome";
		} else if (roleID == 1) { // Financial Manager
//			System.out.println("USER IS A FINANCIAL MANAGER");

			// Save the employee's user ID
			int author = -1;
			List<User> userList = userService.listAllUsers();
			for (User u : userList) {
				if (u.getUsername().equals(username)) {
					author = u.getUsers_id();
				}
			}

			// Save the variable to the session
			req.getSession().setAttribute("loggedUsername", username);
			req.getSession().setAttribute("loggedAuthor", author);

			final Logger loggy = Logger.getLogger(LogInController.class);
			loggy.setLevel(Level.ALL);
			loggy.info("INFO: User logged in: Username: " + username + ".");

			return "/forwarding/financialManagerHome";
		} else { // Unknown role ID
//			System.out.println("USER IS AN UNKNOWN ROLE ID");
			return "/forwarding/incorrectredentials";
		}
	}
}
