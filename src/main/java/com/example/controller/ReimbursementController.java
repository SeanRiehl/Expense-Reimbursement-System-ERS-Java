package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Reimbursement;
import com.example.service.ReimbursementService;
import com.example.service.ReimbursementServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementController {
	static ReimbursementService reimbursementService = new ReimbursementServiceImpl();

	public static void listAllReimbursements(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN REIMBURSEMENT CONTROLLER 1");

		List<Reimbursement> reimbursementList = reimbursementService.listAllReimbursements();

		if (reimbursementList.size() == 0) {
//			System.out.println("ERROR: NO REIMBURSEMENTS FOUND 1");
		} else {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(reimbursementList));
		}
	}

	public static void listAllReimbursementsByStatus(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN REIMBURSEMENT CONTROLLER 2");

		List<Reimbursement> reimbursementList = reimbursementService
				.listAllReimbursementsByStatus(Integer.parseInt(req.getParameter("status")));

		if (reimbursementList.size() == 0) {
//			System.out.println("ERROR: NO REIMBURSEMENTS FOUND 2");
		} else {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(reimbursementList));
		}
	}

	public static void listAllReimbursementsByAuthor(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN REIMBURSEMENT CONTROLLER 3");

		List<Reimbursement> reimbursementList = reimbursementService
				.listAllReimbursementsByAuthor((int) req.getSession().getAttribute("loggedAuthor"));

		if (reimbursementList.size() == 0) {
//			System.out.println("ERROR: NO REIMBURSEMENTS FOUND 3");
		} else {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(reimbursementList));
		}
	}

	public static void addReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN REIMBURSEMENT CONTROLLER 4");

		float amount = Float.parseFloat(req.getParameter("amount"));
		String description = req.getParameter("description");
		int loggedAuthor = (int) req.getSession().getAttribute("loggedAuthor");
		int type_id = Integer.parseInt(req.getParameter("type"));

		reimbursementService.reimbursementCreation(amount, description, loggedAuthor, type_id);
	}

	public static void respondReimbursement(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
//		System.out.println("IN REIMBURSEMENT CONTROLLER 5");

		int reimb_id = Integer.parseInt(req.getParameter("reimb_id"));
		int author = Integer.parseInt(req.getParameter("author"));
		int resolver = (int) req.getSession().getAttribute("loggedAuthor");
		int status_id = Integer.parseInt(req.getParameter("status_id"));

		reimbursementService.handleReimbursemnet(reimb_id, author, resolver, status_id);
	}
}
