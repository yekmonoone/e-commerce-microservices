package com.example.paymentService.Model;

import nonapi.io.github.classgraph.json.Id;

public class Payment {
	private String paymentId;
	private int userId;
	private double amount;
	private String status;
	
	public Payment() {
	}

	//Getters-Setters
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
