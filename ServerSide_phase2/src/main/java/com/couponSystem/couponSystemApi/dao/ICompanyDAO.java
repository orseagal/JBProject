package com.couponSystem.couponSystemApi.dao;

import java.util.Collection;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



public interface ICompanyDAO extends IDAO<Company>  {

    Collection<Company> getAllCompanies() throws CouponSystemException;
	
	//boolean passwordMatches(long id, String password) throws CouponSystemException;
	
	Long login(String user, String password) throws CouponSystemException;
	
	boolean existByName(String name) throws CouponSystemException;

	boolean existsById(long id) throws CouponSystemException;

}
