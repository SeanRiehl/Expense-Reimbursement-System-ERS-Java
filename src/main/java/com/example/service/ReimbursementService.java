package com.example.service;

import java.util.List;

import com.example.model.Reimbursement;

public interface ReimbursementService {
	public boolean reimbursementCreation(float amount, String description, int author, int type_id);
	public List<Reimbursement> listAllReimbursements();
	public List<Reimbursement> listAllReimbursementsByStatus(int status_id);
	public List<Reimbursement> listAllReimbursementsByAuthor(int author);
	public boolean handleReimbursemnet(int reimb_id, int author, int resolver, int status_id);
}
