package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import com.example.dao.ReimbursementDAO;
import com.example.dao.ReimbursementDAOImpl;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;
import com.example.model.Reimbursement;
import com.example.model.User;

@FixMethodOrder
public class ReimbursementTesting {
	ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	UserDAO userDAO = new UserDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// CREATE
	@Ignore
	@Test
	public void testCreateReimbursementMethod() {
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursements();
		int listSize = reimbursementList.size();

		reimbursementDAO.createReimbursement(100, "Test Case", 1, 0);

		List<Reimbursement> newReimbursementList = reimbursementDAO.readAllReimbursements();
		int newListSize = newReimbursementList.size();

		assertEquals("Testing createReimbursement()", listSize + 1, newListSize);
	}

	// READ
	@Test
	public void testReadAllReimbursementsMethod() {
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursements();
		int listSize = reimbursementList.size();

		assertEquals("Testing readAllReimbursements()", 9, listSize);
	}

	@Test
	public void testReadAllReimbursementsByStatusMethod() {
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursementsByStatus(1);
		int listSize = reimbursementList.size();

		assertEquals("Testing readAllReimbursementsByStatus()", 3, listSize);
	}

	@Test
	public void testReadAllReimbursementsByAuthorMethod() {
		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursementsByAuthor(3);
		int listSize = reimbursementList.size();

		assertEquals("Testing readAllReimbursementsByAuthor()", 1, listSize);
	}

	@Test
	public void testReadAllUsersMethod() {
		List<User> userList = userDAO.readAllUsers();
		int listSize = userList.size();

		assertEquals("Testing readAllUsers()", 4, listSize);
	}

	// UPDATE
	@Ignore
	@Test
	public void testUpdateReimbursementMethod() {
		int status_id = -1;

		reimbursementDAO.updateReimbursement(11, 1, 2, 1);

		List<Reimbursement> reimbursementList = reimbursementDAO.readAllReimbursements();

		for (Reimbursement r : reimbursementList) {
			if (r.getReimb_id() == 11) {
				status_id = r.getStatus_id();
			}
		}

		assertEquals("Testing updateReimbursement()", 1, status_id);
	}
}
