package com.couponSystem.couponSystemApi.api;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.beans.PasswordBean;
import com.couponSystem.couponSystemApi.controllers.CustomerController;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


@Path("/customers")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })

public class CustomerApi {

	private CustomerController customerController;

	public CustomerApi() throws CouponSystemException {
		customerController = new CustomerController();
	}
	
	@GET
	@Path("/getAll")
	public Collection<Customer> getAllCustomers() throws CouponSystemException{
		
		
		
		return customerController.getAllCustomers();
			
	}
	@DELETE
	@Path("/{customerId}")
	public void removeCustomer(@PathParam("customerId")long customerId) throws CouponSystemException{
	
		customerController.removeCustomer(customerId);
	}
	
	
	
	@PUT
	@Path("/update-password")
	
	public void updateCustomerByCustomer(PasswordBean passwordBean )throws CouponSystemException {
		System.out.println(passwordBean.getClientType());
	    System.out.println(passwordBean);
		if(passwordBean.getClientType().equals("CUSTOMER")) {
//			System.out.println(passwordBean.getClientType());
			System.out.println("Password change");
			customerController.updateCustomerByCustomer(passwordBean);
			return;
		}
		
		// when the client type not customer
		throw UtilApi.securityBreachException("security breach on : updateCustomerByCustomer user id : ");
}
}
