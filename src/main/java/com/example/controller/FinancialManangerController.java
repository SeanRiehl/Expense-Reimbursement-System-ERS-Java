package com.example.controller;

import javax.servlet.http.HttpServletRequest;

public class FinancialManangerController {
	public static String financialManagerHome(HttpServletRequest req) {
//		System.out.println("IN FINANCIAL MANAGER HOME CONTROLLER");
		return "/resources/html/financialManagerHome.html";
	}
}
