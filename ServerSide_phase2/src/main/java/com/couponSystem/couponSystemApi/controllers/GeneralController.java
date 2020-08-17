package com.couponSystem.couponSystemApi.controllers;


import com.couponSystem.couponSystemApi.beans.UserLogin;
import com.couponSystem.couponSystemApi.dao.sql.GeneralDBDAO;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;

public class GeneralController {

	private GeneralDBDAO generalDBDAO;
	
	
	
	public GeneralController() throws CouponSystemException {
		super();
		this.generalDBDAO = new GeneralDBDAO();
	}



	public UserLogin login(String userName, String password) throws CouponSystemException {
		
	return generalDBDAO.login(userName, password);
		
		
	}
	
	
	
	
	}
	

