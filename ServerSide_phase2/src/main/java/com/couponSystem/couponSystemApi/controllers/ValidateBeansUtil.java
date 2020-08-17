package com.couponSystem.couponSystemApi.controllers;

import java.util.Date;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.beans.Coupon;
import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


public class ValidateBeansUtil {

	private static final int NAME_MIN_LENGHT = 5;
	private static final int NAME_MAX_LENGHT = 50;
	private static final int PASSWORD_MIM_LENGHT = 5;
	private static final int PASSWORD_MAX_LENGHT = 50;

	public ValidateBeansUtil() {
	}

	public static void couponValidate(Coupon coupon) throws CouponSystemException {
		if (coupon.getCompanyId() == 0) {
			throw new CouponSystemException(ErrorType.INVALIDATE_ID_COMPANY_NULL);

		}

//		if (coupon.getCompanyId() != companyCookieId) {
//			throw new CouponSystemException(ErrorType.INVALIDATE_ID_COUPON_BY_COMPANY);
//
//		}

		if (coupon.getTitle() == null) {

			throw new CouponSystemException(ErrorType.INVALIDATE_TITLE);
		} else if (coupon.getTitle().length() < 3) {
			throw new CouponSystemException(ErrorType.INVALIDATE_NAME_SHORT);

		}

		if (coupon.getAmount() < 1) {
			throw new CouponSystemException(ErrorType.INVALIDATE_AMOUNT);

		}

		if (coupon.getPrice() < 0) {
			throw new CouponSystemException(ErrorType.INVALIDATE_PRICE);
		}

		if (coupon.getEndDate().before(new Date((System.currentTimeMillis() - 100000)))) {
			throw new CouponSystemException(ErrorType.INVALIDATE_DATE_END_DATE);
		}

		if (coupon.getStartDate().getTime() > coupon.getEndDate().getTime()) {
			throw new CouponSystemException(ErrorType.INVALIDATE_DATE);
		}

		if (coupon.getType() == null) {

			throw new CouponSystemException(ErrorType.INVALIDATE_TYPE);
		}

	}

	public static void customerValidate(Customer customer) throws CouponSystemException {
		
			validateName(customer.getName());
			
			validatePassword(customer.getPassword());
		
			validateEmail(customer.getEmail());
		
	}
	
	public static void companyValidate(Company company) throws CouponSystemException {
		
		validateName(company.getName());
		
		validatePassword(company.getPassword());
		
		validatePassword(company.getEmail());
	}
	
	
	public static void validateName(String beansName) throws CouponSystemException {
		if (beansName == null) {
			throw new CouponSystemException(ErrorType.INVALIDATE_NAME);
		}

		if (beansName.length() < NAME_MIN_LENGHT || beansName.length() > NAME_MAX_LENGHT) {

			throw new CouponSystemException("The name you inserted is too short. it must be bigger then : " + NAME_MIN_LENGHT,ErrorType.INVALIDATE_NAME_SHORT);
		}

		if (beansName.length() > NAME_MAX_LENGHT) {

			throw new CouponSystemException("The name you inserted is too big. it must be smaller then:",
					ErrorType.INVALIDATE_NAME_LONG);
		}
	}

	public static void validatePassword(String password) throws CouponSystemException {
		if(password == null) {
			throw new CouponSystemException(ErrorType.INVALIDATE_PASSWORD);
		}
		
		if(password.length()< PASSWORD_MIM_LENGHT){
		throw new CouponSystemException("The password you inserted is too short. it must be bigger then :"+ PASSWORD_MIM_LENGHT , ErrorType.INVALIDATE_PASSWORD_SHORT);
	}
		
		if(password.length()> PASSWORD_MAX_LENGHT) {
			throw new CouponSystemException("The password you inserted is too long. it must be bigger then :"+ PASSWORD_MAX_LENGHT , ErrorType.INVALIDATE_PASSWORD_LONG);
			
		}
	}
	
	public static void validateEmail(String email) throws CouponSystemException {
		
		if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
			throw new CouponSystemException(ErrorType.INVALIDATE_EMAIL);
			
		}
		
	}
		
	
	
	


}