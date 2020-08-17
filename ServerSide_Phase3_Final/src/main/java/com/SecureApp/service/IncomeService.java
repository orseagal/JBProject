package com.SecureApp.service;

import com.SecureApp.entity.Income;

import java.util.List;

public interface IncomeService {

	public void storeIncome(Income income); 
	
	public List<Income> viewAllIncome();
	
	public List<Income> viewIncomeByCustomer(long customerId);
	
	public List<Income>  viewIncomeByCompany(long companyId);
	
}
