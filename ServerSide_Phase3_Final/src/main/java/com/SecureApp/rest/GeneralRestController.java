package com.SecureApp.rest;

import com.SecureApp.entity.Coupon;
import com.SecureApp.entity.User;
import com.SecureApp.service.CouponService;
import com.SecureApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@CrossOrigin(origins = "https://couponsystemstorage.web.app", allowCredentials = "true")

@RestController
@RequestMapping("/general")
public class GeneralRestController {

	@Autowired
	UserService userService;

	@Autowired
	CouponService couponService;

	//-------------// GENERAL //-------------
	@GetMapping("/byCategory/{category}")
	public List<Coupon> getAllByCategory(@PathVariable String category) {
		return couponService.getAllByCategory(category);
	}

	@GetMapping("/getAll")
	public List<Coupon> getAll() {
		return couponService.getAll();
	}
	
	@GetMapping("/getById/{couponId}")
	public Coupon getCouponById(@PathVariable long couponId) {
		return couponService.getCouponById(couponId);
	}
	
	@PostMapping("/createCustomer")
	public long CreateCustomer(@RequestBody User customer) {
		return userService.CreateCustomer(customer);
	}
}