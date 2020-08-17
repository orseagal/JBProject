package com.couponSystem.couponSystemApi.api;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.beans.UserLogin;
import com.couponSystem.couponSystemApi.controllers.CompanyController;
import com.couponSystem.couponSystemApi.controllers.CustomerController;
import com.couponSystem.couponSystemApi.controllers.GeneralController;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("")

public class LoginApi {
	private CompanyController companyController;
	private CustomerController customerController;
	
	public LoginApi() throws CouponSystemException{
		this.companyController= new CompanyController();
		this.customerController= new CustomerController();
	}
	@POST
	@Path("/login")
	public UserLogin login(UserLogin userLogin) throws CouponSystemException {
		
		if(userLogin.getName().equals("ADMIN") && userLogin.getPassword().equals("123456")) {
			UserLogin userloginRes = new UserLogin();
			userloginRes.setId((long) 777);
			userloginRes.setType("ADMIN");
			userloginRes.setName("ADMIN");
			return userloginRes;
		}
		
		GeneralController generalController= new GeneralController();
		UserLogin userloginRes= generalController.login(userLogin.getName(), userLogin.getPassword());
	    System.out.println("userloginRes: " + userloginRes);
	

		if(userloginRes !=null) {
			System.out.println("at login");
			System.out.println(userLogin.getName());
			return userloginRes;
		}
		else {
			throw new CouponSystemException(ErrorType.LOGIN);
		}
		
	}
	
	
	@POST
	@Path("/signup")
	public long createUser(UserLogin userLogin) throws CouponSystemException {
		System.out.println(userLogin);
		switch(userLogin.getType()) {
		case "COMPANY":
			Company company = new Company();
			company.setEmail(userLogin.getEmail());
			company.setName(userLogin.getName());
			company.setPassword(userLogin.getPassword());
			System.out.println(company);
			//CompanyController companyController= new CompanyController();
			return companyController.createCompany(company);
			
			
		case"CUSTOMER":
			Customer customer = new Customer();
			customer.setName(userLogin.getName());
			customer.setEmail(userLogin.getEmail());
			customer.setPassword(userLogin.getPassword());
			System.out.println(customer);
			//CustomerController customerController= new CustomerController();
			return customerController.createCustomer(customer);
			
		default:
			throw new CouponSystemException(ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);
		}
	
	}
	
	
}
