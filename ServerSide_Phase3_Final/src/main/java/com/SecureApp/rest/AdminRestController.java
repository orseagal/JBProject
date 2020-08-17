package com.SecureApp.rest;

import com.SecureApp.entity.Income;
import com.SecureApp.entity.User;
import com.SecureApp.service.CouponService;
import com.SecureApp.service.IncomeService;
import com.SecureApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	UserService userService;

	@Autowired
	CouponService couponService;

	@Autowired
	IncomeService incomeService;

	@PostMapping("/createCompany")
	public long CreateCompany(@RequestBody User company) {
		return userService.CreateCompany(company);
	}

	@GetMapping("/allCompanies")
	public List<User> getAllCompanies() {
		return userService.getAllCompanies();
	}

	@GetMapping("/allCustomers")
	public List<User> getAllCustomers() {
		return userService.getAllCustomers();
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable long userId) {
		userService.deleteUser(userId);
	}

	@GetMapping("/allIncomes")
	public List<Income> viewAllIncomes() {
		return incomeService.viewAllIncome();
	}

	@GetMapping("/incomeByCompany/{companyId}")
	public List<Income> viewIncomeByCompany(@PathVariable long companyId) {
		return incomeService.viewIncomeByCompany(companyId);
	}

	@GetMapping("/incomeByCustomer/{customerId}")
	public List<Income> viewIncomeByCustomer(@PathVariable long customerId) {
		return incomeService.viewIncomeByCustomer(customerId);
	}
}
