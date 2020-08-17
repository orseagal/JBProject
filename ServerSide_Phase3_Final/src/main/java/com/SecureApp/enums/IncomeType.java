package com.SecureApp.enums;

public enum IncomeType {

	CUSTOMER_PURCHASE("Coupon purchase by customer"),
	COMPANY_NEW_COUPON("Coupon created by company"),
	COMPANY_UPDATE_COUPON("Coupon updated by company");
	
	private final String description;
	
	private IncomeType(String message) {
		this.description = message;
	}

	public String getMessage() {
		return description;
	}
	
	
	
}
