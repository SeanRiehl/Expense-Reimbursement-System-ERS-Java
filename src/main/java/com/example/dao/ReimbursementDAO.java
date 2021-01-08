package com.example.dao;

import java.util.List;

import com.example.model.Reimbursement;

public interface ReimbursementDAO {
	// CREATE
	public void createReimbursement(float amount, String description, int author, int type_id);

	// READ
	public List<Reimbursement> readAllReimbursements();
	public List<Reimbursement> readAllReimbursementsByStatus(int status_id);
	public List<Reimbursement> readAllReimbursementsByAuthor(int author);

	// UPDATE
	public void updateReimbursement(int reimb_id, int author, int resolver, int status_id);

	// DELETE
}
