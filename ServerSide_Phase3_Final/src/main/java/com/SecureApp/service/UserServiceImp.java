package com.SecureApp.service;

import com.SecureApp.dao.CouponRepositry;
import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.PasswordBean;
import com.SecureApp.entity.User;
import com.SecureApp.enums.ErrorType;
import com.SecureApp.exceptionHander.CouponSystemException;
import com.SecureApp.rest.ValidateBeansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepositry userRepoitory;

	@Autowired
	CouponRepositry couponsRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public long CreateCustomer(User customer) throws CouponSystemException {
		if (userRepoitory.existsByUsername(customer.getUsername()) == true) {
			throw new CouponSystemException(ErrorType.NAME_EXIST);
		}
		customer.setId(0);
		customer.setAuthority("ROLE_CUSTOMER");
		userRepoitory.save(customer);
		return customer.getId();
	}

	@Override
	public void updateCompanyPassword(PasswordBean passwordBean, final User userDetails) throws CouponSystemException {
		System.out.println("inside password change");
		User user = userRepoitory.findById(userDetails.getId());
		if (user != null) {
			if (passwordBean.getPassword().equals(user.getPassword())) {
				ValidateBeansUtil.validatePassword(passwordBean.getNewPassword());
				user.setPassword(passwordBean.getNewPassword());
				userRepoitory.save(user);
			} else {
				throw new CouponSystemException(ErrorType.UPDATE_BY_COMPANY_PASSWORD_NOT_MATCHING);
			}
		} else {
			throw new CouponSystemException(ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);
		}

	}

	@Override
	public void updateCustomerPassword(PasswordBean passwordBean, final User userDetails) throws CouponSystemException {
		System.out.println("inside password change");
		User user = userRepoitory.findById(userDetails.getId());
		if (user != null) {
			if (passwordBean.getPassword().equals(user.getPassword())) {
				ValidateBeansUtil.validatePassword(passwordBean.getNewPassword());
				user.setPassword(passwordBean.getNewPassword());
				userRepoitory.save(user);
			} else {
				throw new CouponSystemException(ErrorType.UPDATE_BY_CUSTOMER_PASSWORD_NOT_MATCHING);
			}
		} else {
			throw new CouponSystemException(ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);
		}

	}

	@Override
	public long CreateCompany(User company) throws CouponSystemException {

		if (userRepoitory.existsByUsername(company.getUsername()) == true) {
			throw new CouponSystemException(ErrorType.NAME_EXIST);
		}
		company.setId(0);
		company.setAuthority("ROLE_COMPANY");
		userRepoitory.save(company);
		return company.getId();
	}

	@Override
	public List<User> getAllCompanies() throws CouponSystemException {

		List<User> companies = userRepoitory.getAllByAuthority("ROLE_COMPANY");
		return companies;
	}

	@Override
	public List<User> getAllCustomers() throws CouponSystemException {
		List<User> customers = userRepoitory.getAllByAuthority("ROLE_CUSTOMER");

		return customers;
	}

	@Override
	public void deleteUser(long userId) throws CouponSystemException {

		userRepoitory.deleteById(userId);
	}

	@Override
	public User validateLogin(User userDetails) throws CouponSystemException {

		User user = userRepoitory.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername());
		if (user != null) {
			if (userDetails.getPassword().equals(user.getPassword())) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return user;
			} else {
				throw new CouponSystemException(ErrorType.LOGIN);
			}
		} else {
			throw new CouponSystemException(ErrorType.LOGIN);
		}
	}

}
