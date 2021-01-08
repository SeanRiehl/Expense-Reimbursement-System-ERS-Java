package com.example.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.EmployeeHomeController;
import com.example.controller.FinancialManangerController;
import com.example.controller.LogInController;

public class RequestHelper {
	public static String process(HttpServletRequest req, HttpServletResponse resp) {
//		System.out.println("IN REQUESTHELPER");
//		System.out.println("REQUEST URI: " + req.getRequestURI());

		switch (req.getRequestURI()) {
		case "/Project1/forwarding/login":
//			System.out.println("IN THE FIRST CASE");
			resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return LogInController.login(req, resp);
		case "/Project1/forwarding/employeeHome":
//			System.out.println("IN THE SECOND CASE");
			resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return EmployeeHomeController.employeeHome(req);
		case "/Project1/forwarding/financialManagerHome":
//			System.out.println("IN THE THIRD CASE");
			resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return FinancialManangerController.financialManagerHome(req);
		default:
//			System.out.println("IN THE DEFAULT CASE");
			resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
			return "/resources/html/badlogin.html";
		}
	}
}
