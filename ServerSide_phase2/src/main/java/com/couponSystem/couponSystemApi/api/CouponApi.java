package com.couponSystem.couponSystemApi.api;

import java.util.Collection;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.couponSystem.couponSystemApi.beans.Coupon;
import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.controllers.CompanyController;
import com.couponSystem.couponSystemApi.controllers.CouponController;
import com.couponSystem.couponSystemApi.controllers.CustomerController;
import com.couponSystem.couponSystemApi.enums.CouponType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;




@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/coupons")
public class CouponApi {

	CouponController couponController;
	CompanyController comapnyController;
	CustomerController customerController;

	public CouponApi() throws CouponSystemException {
		this.couponController = new CouponController();
		this.comapnyController = new CompanyController();
		this.customerController = new CustomerController();
	}

	@POST
	@Path("/create")
	public long CreateCoupon(Coupon coupon) throws CouponSystemException {
		System.out.println(coupon);

		System.out.println("at create coupon");
		if (comapnyController.getComapny(coupon.getCompanyId()) != null) {
			System.out.println(coupon.getCompanyId());
			System.out.println("create coupons works");
			return couponController.createCoupon(coupon);
		}

		throw UtilApi.securityBreachException("security breach on : createCoupon user id : ");
	}
	
	
	
	@POST
	@Path("/purchase/{customerId}")
	public long purchaseCoupon(@PathParam("customerId")long customerId,Set<Long> couponsCart) throws CouponSystemException {
		
		System.out.println(couponsCart);
		System.out.println(customerId);
		
		Customer userType = customerController.getCustomer(customerId);
		if(userType!=null) {
				couponController.purchaseCoupon(customerId, couponsCart);
				return 1;
		}
		throw UtilApi.securityBreachException("security breach on : purchaseCoupon user id : ");
		
		
		}
		
		
	
	@GET
	@Path("/customerCoupons/{customerId}")
	public Collection<Coupon> getCustomerCoupons(@PathParam("customerId")long customerId) throws CouponSystemException {
		System.out.println("Get Purchase Coupons Works 1!!");
		
			Customer customer = customerController.getCustomer(customerId);
			if(customer!=null) {
				System.out.println("Get Purchase Coupons Works 2!!");
			return couponController.getCustomerCoupons(customerId);
		
					
				}
			
		
		//when the client type not customer
		throw UtilApi.securityBreachException("security breach on : getCustomerCoupons user id : ");
	}
	
	
	
	
	@GET
	@Path("/companyCoupons/{companyId}")
	public Collection<Coupon> getCompanyCoupons(@PathParam("companyId")long companyId) throws CouponSystemException {
		
		if(comapnyController.getComapny(companyId)!=null) {
			
			return couponController.getCompanyCoupons(companyId);
		}
		
		throw UtilApi.securityBreachException("security breach on : getCompanyCoupons user id : ");
	}
	
	@GET
	@Path("/getAll")
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {
		return couponController.getAllCoupons();
	}
	
	@GET
	@Path("/{id}")
	public Coupon getCoupon(@PathParam("id") long couponId) throws CouponSystemException {
		return couponController.getCoupon(couponId);
	}
	
	@GET
	@Path("/byType")
	public Collection<Coupon> getCompanyCouponsByType(@QueryParam("type") String type) throws CouponSystemException {
	
			if(type != null) {
				
				CouponType couponType = UtilApi.checkCouponType(type);
				return couponController.getCouponsByType(couponType);
			}

		throw UtilApi.securityBreachException("security breach on : getCompanyCouponsByType user id : ");
	}
	
	
	
	@DELETE
	@Path("/{companyId}/{couponId}")
	public void removeCompanyCouponByCoupon(@PathParam("companyId") long companyId, @PathParam("couponId") long couponId) throws CouponSystemException {
		
		
		if (couponController.getCoupon(couponId) != null) {
			
			couponController.removeCompanyCouponsByCoupon(couponId, companyId);
			return;
		}
		throw UtilApi.securityBreachException("security breach on : removeCompanyCouponByCoupon user id : ");
	}
	
}
