package com.couponSystem.couponSystemApi.api;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.beans.PasswordBean;
import com.couponSystem.couponSystemApi.controllers.CompanyController;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



@Path("/companies")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CompanyApi {

	
	private CompanyController companyCntroller;

	public CompanyApi() throws CouponSystemException {
		companyCntroller = new CompanyController();
	}

	@GET
	@Path("/getAll")
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		
		
		return companyCntroller.getAllCompanies();
	}
	
	@POST
	public long createCompany(Company company) throws CouponSystemException {
		
		return companyCntroller.createCompany(company);
		
		
	}
	
	
	@PUT
	@Path("/update-password")
	public void updatePasswordByCompany( PasswordBean passwordBean)
			throws CouponSystemException {
		
		if (passwordBean.getClientType().equals("COMPANY")) {
			companyCntroller.updateComapnyPasswordByCompany(passwordBean);
			return;
		}
		//when the type not matching to company 
		throw UtilApi.securityBreachException("security breach on : byCompanyPassword user id : ");
	}
	
	
	@DELETE
	@Path("/{companyId}")
	public void removeCompany(@PathParam("companyId")long companyId) throws CouponSystemException {
		
		companyCntroller.removeCompany(companyId);
	}
	
	
}
