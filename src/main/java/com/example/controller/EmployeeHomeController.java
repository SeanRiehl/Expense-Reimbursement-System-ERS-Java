package com.example.controller;

import javax.servlet.http.HttpServletRequest;

public class EmployeeHomeController {
	public static String employeeHome(HttpServletRequest req) {
//		System.out.println("IN EMPLOYEE HOME CONTROLLER");
		return "/resources/html/employeeHome.html";
	}
}
