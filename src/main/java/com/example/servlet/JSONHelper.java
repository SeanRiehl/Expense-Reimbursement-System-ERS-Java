package com.example.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.ReimbursementController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JSONHelper {
	public static void process(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN JSONHELPER");
//		System.out.println("JSON REQUEST URI: " + req.getRequestURI());

		switch (req.getRequestURI()) {
		case "/Project1/JSON/reimbursement":
//			System.out.println("IN THE JSON FIRST CASE");
			ReimbursementController.listAllReimbursements(req, resp);
			break;
		case "/Project1/JSON/reimbursementByStatus":
//			System.out.println("IN THE JSON SECOND CASE");
			ReimbursementController.listAllReimbursementsByStatus(req, resp);
			break;
		case "/Project1/JSON/reimbursementByAuthor":
//			System.out.println("IN THE JSON THIRD CASE");
			ReimbursementController.listAllReimbursementsByAuthor(req, resp);
			break;
		case "/Project1/JSON/send":
//			System.out.println("IN THE JSON FOURTH CASE");
			ReimbursementController.addReimbursement(req, resp);
			break;
		case "/Project1/JSON/update":
//			System.out.println("IN THE JSON FIFTH CASE");
			ReimbursementController.respondReimbursement(req, resp);
			break;
		default:
//			System.out.println("IN THE JSON DEFAULT CASE");
			break;
		}
	}
}
