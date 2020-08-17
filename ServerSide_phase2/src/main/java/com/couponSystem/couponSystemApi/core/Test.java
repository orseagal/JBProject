package com.couponSystem.couponSystemApi.core;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import com.couponSystem.couponSystemApi.beans.Company;
import com.couponSystem.couponSystemApi.beans.Customer;
import com.couponSystem.couponSystemApi.beans.PurchaseBean;
import com.couponSystem.couponSystemApi.controllers.CompanyController;
import com.couponSystem.couponSystemApi.controllers.CouponController;
import com.couponSystem.couponSystemApi.controllers.CustomerController;
import com.couponSystem.couponSystemApi.controllers.GeneralController;
import com.couponSystem.couponSystemApi.dao.sql.CompanyDBDAO;
import com.couponSystem.couponSystemApi.dao.sql.CouponDBDAO;
import com.couponSystem.couponSystemApi.dao.sql.GeneralDBDAO;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;




public class Test {

	public static void main(String[] args) throws ParseException, CouponSystemException {
		
		
		
		//Set<Long> test = new HashSet<>();
			
		long[] test = {333,44,556,666};	
			
		long l1 = 5550;
		long l2 = 948833;
		long l3 = 22242424;
		
//		test.add(l1);
//		test.add(l2);
//		test.add(l3);
		
		
		PurchaseBean bean = new PurchaseBean();
		
		bean.setCoupons(test);
		long[] test2= bean.getCoupons();
		
		for (int i = 0; i < test2.length; i++) {
			System.out.println(test2[i]);
		}
		CustomerController customerController = new CustomerController();
		CouponController couponController = new CouponController();
		
		System.out.println(couponController.getCustomerCoupons(44));
		System.out.println(customerController.getCustomer((long) 44));
		//System.out.println(bean.getCoupons());
		
	//	Database.initialize();
		
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = "2014-02-11";
//		Date dateObject = sdf.parse(dateString); // Handle the ParseException here
	
		// Date date = new GregorianCalendar(2019, Calendar.SEPTEMBER, 28).getTime();
		 
		// System.out.println(date);
		 
		
//		
//		CustomerController customerController = new CustomerController();
//		CompanyController companyController = new CompanyController();
//		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
//		CouponDBDAO couponDBDAO = new CouponDBDAO();
//		
//		Customer c1 = new Customer();
//		c1.setEmail("Sharon@gmail.com");
//		c1.setName("SharonMBC");
//		c1.setPassword("12245678");
//		System.out.println(c1);
		
		//customerController.createCustomer(c1);
		
//		Company comp1 = new Company();
//		comp1.setEmail("comp5@gmail");
//		comp1.setName("comp5");
//		comp1.setPassword("414444544");
//		
//		System.out.println(comp1);
//		
		//companyController.createCompany(comp1);
		
		
//		GeneralController generalControler = new GeneralController();
//		
//		System.out.println(generalControler.login("Sharon@gmail.com", "12245678"));
		
//		System.out.println(companyController.getAllCompanies());
		
//		System.out.println(companyDBDAO.getAllCompanies());
		
//		System.out.println(couponDBDAO.getCustomerCoupons(1));
//		
//		System.out.println(customerController.removeCustomer((long) 1));
		
		//CustomerController custController = new CustomerController();
		
		//System.out.println(custController.getAllCustomers());
		
		 
//		CompanyController compController = new CompanyController();
//		
//		//System.out.println(compController.getComapny(55));
//		
//		
//		
//		
//		
//		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
//		//sdf.setTimeZone(TimeZone.getTimeZone("UTC "));
//		String dateString = "2019-9-30 00:00 UTC";
//		Date dateObject = sdf.parse(dateString); // Handle the ParseException here
//		
//		System.out.println(dateObject);
//		//System.out.println(dateObject.getTime());
//		System.out.println(new Date());
//		
//		
//		
//		Date endsdate = dateObject;
//		
//		
//		
//		
//		Coupon c1 = new Coupon();
//		c1.setCompanyId(1223);
//		c1.setStartDate(new Date());
//		c1.setEndDate(endsdate);
//		c1.setTitle("food");
//		c1.setPrice(5060);
//		c1.setType(CouponType.FOOD);
//		c1.setMessage("message");
//		c1.setImage("image");
//		
//		Coupon c2 = new Coupon();
//		c2.setCompanyId(1663);
//		c2.setStartDate(new Date());
//		c2.setEndDate(endsdate);
//		c2.setTitle("health");
//		c2.setPrice(500);
//		c2.setType(CouponType.HEALTH);
//		c2.setMessage("message");
//		c2.setImage("image");
//		
//		Coupon c3 = new Coupon();
//		c3.setCompanyId(1243);
//		c3.setStartDate(new Date());
//		c3.setEndDate(endsdate);
//		c3.setTitle("electricity");
//		c3.setPrice(5060);
//		c3.setType(CouponType.ELECTRICITY);
//		c3.setMessage("message");
//		c3.setImage("image");
//		
//		Coupon c4 = new Coupon();
//		c4.setCompanyId(1723);
//		c4.setStartDate(new Date());
//		c4.setEndDate(endsdate);
//		c4.setTitle("resturants");
//		c4.setPrice(80);
//		c4.setType(CouponType.RESTAURANTS);
//		c4.setMessage("message");
//		c4.setImage("image");
//		
//      	Coupon c5 = new Coupon();
////		c5.setCompanyId(1283);
////		c5.setTitle("traveling");
////		c5.setPrice(860);
////		c5.setType(CouponType.TRAVELING);
////		c5.setMessage("message");
////		c5.setImage("image");
//	
//      	c5.setCompanyId(4);
//      	c5.setAmount(1);
//      	c5.setStartDate(new Date());
//      	c5.setEndDate(endsdate);
//		c5.setTitle("traveling_new");
//		c5.setPrice(8690);
//		c5.setType(CouponType.TRAVELING);
//		c5.setMessage("message");
//		c5.setImage("image");
//		
//		
//		
//		Coupon c6 = new Coupon();
//		
//		c6.setCompanyId(1663);
//		c6.setStartDate(new Date());
//		c6.setEndDate(endsdate);
//		c6.setTitle("traveling2");
//		c6.setPrice(860);
//		c6.setType(CouponType.TRAVELING);
//		c6.setMessage("message");
//		c6.setImage("image");
//		
//         Coupon c7 = new Coupon();
//		
//		c7.setCompanyId(1663);
//		c7.setStartDate(new Date());
//		c7.setEndDate(endsdate);
//		c7.setTitle("traveling3");
//		c7.setPrice(8640);
//		c7.setType(CouponType.TRAVELING);
//		c7.setMessage("message");
//		c7.setImage("image");
//		
//		 Coupon c8 = new Coupon();
//			
//			c8.setCompanyId(1663);
//			c8.setStartDate(new Date());
//			c8.setEndDate(endsdate);
//			c8.setTitle("traveling17");
//			c8.setPrice(8560);
//			c8.setType(CouponType.TRAVELING);
//			c8.setMessage("message");
//			c8.setImage("image");
//		
//		
//		
//		
//		
//		Company comp1 = new Company();
//		comp1.setEmail("fafafa@gmail.com");
//		comp1.setName("comp1");
//		comp1.setPassword("12234");
//		
//		Company comp2 = new Company();
//		comp2.setEmail("sddf@gmail.com");
//		comp2.setName("comp2-new");
//		comp2.setPassword("23553f");
//		//comp2.setId(2);
//		
//		Company comp3 = new Company();
//		comp3.setEmail("sdd3f@gmail.com");
//		comp3.setName("comp3");
//		comp3.setPassword("2353353f");
//		//comp2.setId(2);
//		
//		Company comp4 = new Company();
//		comp4.setEmail("sdd4f@gmail.com");
//		comp4.setName("comp4");
//		comp4.setPassword("2355443f");
//		//comp4.setId(2);
//		
//		Company comp5 = new Company();
//		comp5.setEmail("sddf@gmail.com");
//		comp5.setName("comp5");
//		comp5.setPassword("235553f");
//		//comp5.setId(2);
//		
//		Company comp6 = new Company();
//		comp6.setEmail("sddf66@gmail.com");
//		comp6.setName("comp6");
//		comp6.setPassword("2653f");
//		
//		Customer cust1 = new Customer();	
//		cust1.setEmail("cust1_aff@gmail.com");
//		cust1.setName("cust1");
//		cust1.setPassword("123355");
//		
//		Customer cust2 = new Customer();	
//		cust2.setEmail("cust2_af44fhf@gmail.com");
//		cust2.setName("cust2");
//		cust2.setPassword("14323355");
//		//cust2.setId(2);
//		
//		Customer cust3 = new Customer();	
//		cust3.setEmail("cust3_af44fhf@gmail.com");
//		cust3.setName("cust3");
//		cust3.setPassword("1433355");
//		//cust2.setId(2);
//		
//		Customer cust4 = new Customer();	
//		cust4.setEmail("cust4_af44fhf@gmail.com");
//		cust4.setName("cust4");
//		cust4.setPassword("144355");
//		//cust2.setId(2);
//		
//		Customer cust5 = new Customer();	
//		cust5.setEmail("cust5_af44fhf@gmail.com");
//		cust5.setName("cust5");
//		cust5.setPassword("15523355");
//		//cust2.setId(2);
//		
//		Customer cust6 = new Customer();	
//		cust6.setEmail("cust6_6fhf@gmail.com");
//		cust6.setName("cust6");
//		cust6.setPassword("14665");
//		//cust2.setId(2);
//		
//		Customer cust7 = new Customer();	
//		cust7.setEmail("cust7_a7gmail.com");
//		cust7.setName("cust7");
//		cust7.setPassword("1473355");
//		//cust2.setId(2);
//		
//		Customer cust8 = new Customer();	
//		cust8.setEmail("cust8_a8hf@gmail.com");
//		cust8.setName("cust8");
//		cust8.setPassword("148355");
//		//cust2.setId(2);
//		
//		Customer cust9 = new Customer();	
//		cust9.setEmail("cust9_a9f@gmail.com");
//		cust9.setName("cust9");
//		cust9.setPassword("1439355");
//		//cust2.setId(2);
//		
//		Customer cust10 = new Customer();	
//		cust10.setEmail("cust10_a0hf@gmail.com");
//		cust10.setName("cust10");
//		cust10.setPassword("11023355");
//		//cust2.setId(2);
//		
//		Customer cust11 = new Customer();	
//		cust11.setEmail("cust11f@gmail.com");
//		cust11.setName("cust11");
//		cust11.setPassword("14315");
//		//cust2.setId(2);
//		
//		
//		try {
//			CouponDBDAO couponDBDAO = new CouponDBDAO();
//			CompanyDBDAO companyDBDAO = new CompanyDBDAO();
//			CustomerDBDAO customerDBDAO = new CustomerDBDAO();
//			
//			
//			CouponController couponCTRL = new CouponController();
//			
//			couponCTRL.createCoupon(c5, 4);
			
			//couponDBDAO.updateAmount(1, -2);
			
			//System.out.println(customerDBDAO.login("cust7", "1473355"));
			
			//System.out.println(companyDBDAO.login("comp1", "12234"));
			
			//System.out.println(companyDBDAO.getAllCompanies());
			
			
			//System.out.println(couponDBDAO.getAllCoupons());
			
			//System.out.println(customerDBDAO.getAllCustomers());
			//System.out.println(customerDBDAO.getByName("cust10"));
		//	System.out.println(customerDBDAO.existByname("cus"));
			//customerDBDAO.delete(2);
			
			
			
			
//			customerDBDAO.create(cust2);
//			customerDBDAO.create(cust3);
//			customerDBDAO.create(cust4);
//			customerDBDAO.create(cust5);
//			customerDBDAO.create(cust6);
//			customerDBDAO.create(cust7);
//			customerDBDAO.create(cust8);
//			customerDBDAO.create(cust9);
//			customerDBDAO.create(cust10);
		//  customerDBDAO.create(cust11);
			
			//customerDAO.update(cust2);
			
			
		//	customerDAO.create(cust2);
			//System.out.println(customerDAO.read(2));
//			companyDBDAO.create(comp1);
//			companyDBDAO.create(comp2);
//	     	companyDBDAO.create(comp3);
//			companyDBDAO.create(comp4);
//			companyDBDAO.create(comp5);
//			companyDBDAO.create(comp6);
//			
		//	System.out.println(companyDBDAO.existsById(1555));
			//System.out.println(companyDBDAO.existByName("comp1"));
			//System.out.println(couponDBDAO.getCompanyCoupons(1663));
			//System.out.println(companyDBDAO.read(2));
			
			//companyDBDAO.update(comp2);
			
			//System.out.println(couponDBDAO.getCompanyCouponsByPrice(1663, 9000));
			//System.out.println(couponDBDAO.getCompanyCouponsByType(CouponType.FOOD, 1663));
			
		//	System.out.println(couponDBDAO.getCompanyCouponsByDate(endsdate, 1663));
			
			//System.out.println(couponDBDAO.read(34));
			
			//couponDBDAO.delete(6);
			
			//System.out.println(new Date());
			
//			couponDBDAO.create(c1);
//			couponDBDAO.create(c2);
//			couponDBDAO.create(c3);
	//	couponDBDAO.create(c8);
			
			
		//	System.out.println(couponDBDAO.read(16));
			//System.out.println(c8.getEndDate());
			//couponDBDAO.create(c6);
			//couponDBDAO.create(c7);
			//System.out.println(couponDBDAO.titleExists("electricity"));
			
			
//			couponDBDAO.purchaseCoupon(1, 14);
//			couponDBDAO.purchaseCoupon(1, 15);
//			couponDBDAO.purchaseCoupon(1, 16);
//			couponDBDAO.purchaseCoupon(1, 17);
//			couponDBDAO.purchaseCoupon(1, 18);
			//System.out.println(couponDBDAO.getCustomerCoupons(9));
			
			//System.out.println(couponDBDAO.getCustomerCouponsByType(CouponType.FOOD, 9));
			
			//System.out.println(couponDBDAO.getCustomerCouponsByPrice(4000, 9));
			
			//couponDBDAO.removeCustomerCouponByCoupon(1001);
			//couponDBDAO.removeCustomerCouponsByCustomer(9);
			//couponDBDAO.removeCompanyCouponsBycompany(1223);
			//couponDBDAO.removeCustomerCouponsByCompany(1663);
			
		//	System.out.println(couponDBDAO.create(c5));
			//couponDBDAO.update(c5);
			
//		} catch (CouponSystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		

	}

}
