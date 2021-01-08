package com.example.service;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.example.dao.ReimbursementDAO;
import com.example.dao.ReimbursementDAOImpl;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;
import com.example.model.Reimbursement;
import com.example.model.User;

public class ReimbursementServiceImpl implements ReimbursementService {
	private UserDAO userDAO = new UserDAOImpl();
	private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("THE STATIC BLOCK HAS FAILED");
			e.printStackTrace();
		}
	}

	@Override
	public boolean reimbursementCreation(float amount, String description, int author, int type_id) {
		final Logger loggy = Logger.getLogger(ReimbursementServiceImpl.class);
		loggy.setLevel(Level.ALL);

		boolean authorIsEmployee = false;

		// Get the user list
		List<User> userList = userDAO.readAllUsers();

		// Search the table for a matching user and confirm they are an employee
		for (User u : userList) {
			if (u.getUsers_id() == author & u.getRole_id() == 0) {
				authorIsEmployee = true;
			}
		}

		// If the user is an employee, create the reimbursement
		if (authorIsEmployee) {
			reimbursementDAO.createReimbursement(amount, description, author, type_id);
			loggy.info("INFO: New reimbursement created: Amount: " + amount + ", Description: " + description
					+ ", Author: " + author + ", Type ID: " + type_id + ".");
			return authorIsEmployee;
		} else {
			return authorIsEmployee;
		}
	}

	@Override
	public List<Reimbursement> listAllReimbursements() {
		// Get the reimbursement list
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursements();

		return reimbursementList;
	}

	@Override
	public List<Reimbursement> listAllReimbursementsByStatus(int status_id) {
		// Get the reimbursement list
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursementsByStatus(status_id);

		return reimbursementList;
	}

	@Override
	public List<Reimbursement> listAllReimbursementsByAuthor(int author) {
		// Get the reimbursement list
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursementsByAuthor(author);

		return reimbursementList;
	}

	@Override
	public boolean handleReimbursemnet(int reimb_id, int author, int resolver, int status_id) {
		final Logger loggy = Logger.getLogger(ReimbursementServiceImpl.class);
		loggy.setLevel(Level.ALL);

		boolean reimbursementFound = false;

		// Get the reimbursement list
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursements();

		for (Reimbursement r : reimbursementList) {
			if (r.getReimb_id() == reimb_id & r.getAuthor() == author) {
				if (r.getStatus_id() == 0) {
					reimbursementFound = true;
				}
			}
		}

		if (reimbursementFound) {
			reimbursementDAO.updateReimbursement(reimb_id, author, resolver, status_id);
			loggy.info("INFO: Reimbursement updated: Reimbursement ID: " + reimb_id + ", Author: " + author
					+ ", Resolver: " + resolver + ", Status ID: " + status_id + ".");
			return reimbursementFound;
		} else {
			return reimbursementFound;
		}
	}
}
