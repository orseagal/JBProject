package com.couponSystem.couponSystemApi.controllers;

import java.util.Collection;

import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.beans.PasswordBean;
import com.couponSystem.couponSystemApi.dao.ICouponDAO;
import com.couponSystem.couponSystemApi.dao.ICustomerDAO;
import com.couponSystem.couponSystemApi.dao.sql.CouponDBDAO;
import com.couponSystem.couponSystemApi.dao.sql.CustomerDBDAO;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


public class CustomerController {

	private ICustomerDAO customerDAO;
	private ICouponDAO couponDAO;
	

	public CustomerController() throws CouponSystemException {
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
		
	}

	public long createCustomer(Customer customer) throws CouponSystemException {

		ValidateBeansUtil.customerValidate(customer);

		if (customerDAO.existByname(customer.getName())) {
			System.out.println("Name Exist");
			throw new CouponSystemException(ErrorType.NAME_EXIST);
		}
		
		if (customerDAO.existByEmail(customer.getEmail())) {
			System.out.println("Email Exist");
			throw new CouponSystemException(ErrorType.EMAIL_EXIST);
		}
		return customerDAO.create(customer);

	}

	public void removeCustomer(Long customerId) throws CouponSystemException {

		if (customerDAO.existByid(customerId)) {
			
			couponDAO.removeCustomerCouponsByCustomer(customerId);

			customerDAO.delete(customerId);
		} else {

			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {

		Customer updateCustomer = customerDAO.read(customer.getId());

		if (updateCustomer != null) {

			updateCustomer.setPassword(customer.getPassword());

			ValidateBeansUtil.customerValidate(updateCustomer);

			customerDAO.update(updateCustomer);
		} else {

			throw new CouponSystemException(ErrorType.CUSTOMER_NOT_EXISTS);
		}

	}

	public void updateCustomerByCustomer(PasswordBean passwordBean)
			throws CouponSystemException {

		if (passwordBean!=null) {

			Customer updateCustomer = customerDAO.read(passwordBean.getId());
			System.out.println("updateCustomer" + updateCustomer);
			if (passwordBean.getPassword().equals(updateCustomer.getPassword())) {

				ValidateBeansUtil.validatePassword(passwordBean.getNewPassword());

				updateCustomer.setPassword(passwordBean.getNewPassword());

				customerDAO.update(updateCustomer);
			} else {

				throw new CouponSystemException(ErrorType.UPDATE_BY_CUSTOMER_PASSWORD_NOT_MATCHING);
			}
		} else {

			throw new CouponSystemException(ErrorType.UPDATE_BY_CUSTOMER);
		}

		// customerDAO.update(t);

	}

	public Customer getCustomer(Long customerId) throws CouponSystemException {
		Customer customer = customerDAO.read(customerId);

		if (customer != null) {

			return customer;
		} else {

			throw new CouponSystemException(ErrorType.CUSTOMER_NOT_EXISTS);
		}

	}
	
	public Collection<Customer> getAllCustomers() throws CouponSystemException{
			
		return customerDAO.getAllCustomers();	
	}
	
	
	public long login(String user, String password) throws CouponSystemException {
		
		return customerDAO.login(user, password);
		
		
	}

}
