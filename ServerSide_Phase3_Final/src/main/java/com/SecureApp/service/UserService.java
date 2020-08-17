package com.SecureApp.service;

import com.SecureApp.entity.PasswordBean;
import com.SecureApp.entity.User;
import com.SecureApp.exceptionHander.CouponSystemException;

import java.util.List;

public interface UserService {
	
	public long CreateCustomer(User customer) throws CouponSystemException;
	
	public void updateCompanyPassword(PasswordBean passwordBean, final User userDetails) throws CouponSystemException;
	
	public void updateCustomerPassword(PasswordBean passwordBean, final User userDetails) throws CouponSystemException;
	
	public long CreateCompany(User company) throws CouponSystemException;
	
	public List<User> getAllCompanies() throws CouponSystemException;
	
	public List<User> getAllCustomers() throws CouponSystemException;
	
	public void deleteUser(long userId) throws CouponSystemException;
	
    //public User validateLogin(final UserDetails userDetails) throws CouponSystemException;
    
	 public User validateLogin(final User userDetails) throws CouponSystemException;
	    
}
