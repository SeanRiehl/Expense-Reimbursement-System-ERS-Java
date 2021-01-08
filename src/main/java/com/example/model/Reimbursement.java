package com.example.model;

import java.util.Arrays;

public class Reimbursement {
	private int reimb_id;
	private float amount;
	private String submitted;
	private String resolved;
	private String description;
	private byte[] receipt;
	private int author;
	private int resolver;
	private int status_id;
	private int type_id;

	// No args constructor
	public Reimbursement() {
	}

	// All args constructor
	public Reimbursement(int reimb_id, float amount, String submitted, String resolved, String description,
			byte[] receipt, int author, int resolver, int status_id, int type_id) {
		super();
		this.reimb_id = reimb_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	// Getters and setters
	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	// toString override
	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", author="
				+ author + ", resolver=" + resolver + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
}
