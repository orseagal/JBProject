package com.SecureApp.service;

import com.SecureApp.dao.IncomeRepositry;
import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.Income;
import com.SecureApp.entity.User;
import com.SecureApp.enums.ErrorType;
import com.SecureApp.exceptionHander.CouponSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImp implements IncomeService {

	@Autowired
	UserRepositry userRepoitory;

	@Autowired
	IncomeRepositry incomeRepositry;

	@Override
	public synchronized void storeIncome(Income income) {
		System.out.println("Income created! " + income);
		incomeRepositry.save(income);

	}

	@Override
	public synchronized List<Income> viewAllIncome() {
		return incomeRepositry.findAll();
	}

	@Override
	public synchronized List<Income> viewIncomeByCustomer(long customerId) {

		User user = userRepoitory.findById(customerId);
		if (user != null && user.getAuthority().equals("ROLE_CUSTOMER")) {
			String name = user.getUsername();
			return incomeRepositry.getAllByName(name);
		} else {
			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

	@Override
	public synchronized List<Income> viewIncomeByCompany(long companyId) {

		User user = userRepoitory.findById(companyId);
		if (user != null && user.getAuthority().equals("ROLE_COMPANY")) {
			String name = user.getUsername();
			return incomeRepositry.getAllByName(name);
		} else {
			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

}
