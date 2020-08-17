package com.couponSystem.couponSystemApi.dao;

import java.util.Collection;

import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



public interface ICustomerDAO extends IDAO<Customer>{

	Collection<Customer> getAllCustomers() throws CouponSystemException;

	boolean existByname(String name) throws CouponSystemException;

	boolean existByid(long id) throws CouponSystemException;

	Customer getByName(String name) throws CouponSystemException; 
	
	Long login(String user, String password) throws CouponSystemException;

	boolean existByEmail(String email) throws CouponSystemException;


	
	
	
	
	
	
}
