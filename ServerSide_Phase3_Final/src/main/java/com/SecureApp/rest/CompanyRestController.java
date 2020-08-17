package com.SecureApp.rest;


import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.Coupon;
import com.SecureApp.entity.Income;
import com.SecureApp.entity.PasswordBean;
import com.SecureApp.entity.User;
import com.SecureApp.enums.ErrorType;
import com.SecureApp.exceptionHander.CouponSystemException;
import com.SecureApp.service.CouponService;
import com.SecureApp.service.IncomeService;
import com.SecureApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/companies")
public class CompanyRestController {

	@Autowired
	UserService userService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserRepositry userRepositry;
	@Autowired
	IncomeService incomeService;

	@PostMapping("/createCoupon")
	public void createCoupon(@AuthenticationPrincipal final UserDetails userdetails, @RequestBody Coupon coupon) {
		String companyName = userdetails.getUsername();
		couponService.createCoupon(coupon, companyName);
	}

	@PutMapping("/updateCoupon")
	public void updateCoupon(@AuthenticationPrincipal final UserDetails userdetails, @RequestBody Coupon coupon) {
		String companyName = userdetails.getUsername();
		couponService.updateCoupon(coupon, companyName);
	}

//	@GetMapping("/updateAmount/{couponId}/{amount}")
//	public void updateAmount(@PathVariable long couponId,@PathVariable int amount ) throws CouponSystemException{
//		couponService.updateAmount(couponId, amount);
//	}

	@DeleteMapping("/{companyId}/{couponId}")
	public void deleteCoupon(@PathVariable long companyId, @PathVariable long couponId) throws CouponSystemException {

		// Add deleting validation
		couponService.deleteCoupon(companyId, couponId);
	}

	@GetMapping("/companyCoupons/{companyId}")
	public List<Coupon> getCompanyCoupons(@PathVariable long companyId) throws CouponSystemException {
		return couponService.getCompanyCoupons(companyId);

	}

	@PutMapping("/update-password")
	public void updateCompanyPassword(@AuthenticationPrincipal final UserDetails userdetails,
			@RequestBody PasswordBean passwordBean) throws CouponSystemException {
		User user = userRepositry.findByUsername(userdetails.getUsername());
		userService.updateCompanyPassword(passwordBean, user);

	}

	@GetMapping("/incomeByCompany")
	public List<Income> viewIncomeByCompany(@AuthenticationPrincipal final UserDetails userdetails) {
		User user = userRepositry.findByUsername(userdetails.getUsername());
		if (user != null) {
			return incomeService.viewIncomeByCompany(user.getId());
		} else {
			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

	@GetMapping("/soldCoupons")
	public HashSet<Coupon> getAllSoldCopuon(@AuthenticationPrincipal final UserDetails userdetails) {
		User user = userRepositry.findByUsername(userdetails.getUsername());
		System.out.println("inside Sold coupons");
		if (user != null) {
			long companyId = user.getId();
			List<Coupon> couponsArray = couponService.getCompanySoldCopuon(companyId);
			HashSet<Coupon> hsUnique = new HashSet<Coupon>(couponsArray);
			for (Coupon coupon : hsUnique) {
				coupon.setAmount(Collections.frequency(couponsArray, coupon));
			}

			return hsUnique;
		} else {
			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

}
