package com.SecureApp.service;

import com.SecureApp.dao.CouponRepositry;
import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.Coupon;
import com.SecureApp.entity.Income;
import com.SecureApp.entity.User;
import com.SecureApp.enums.ErrorType;
import com.SecureApp.enums.IncomeType;
import com.SecureApp.exceptionHander.CouponSystemException;
import com.SecureApp.rest.ValidateBeansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImp implements CouponService {

	@Autowired
	UserRepositry userRepoitory;

	@Autowired
	CouponRepositry couponsRepository;

	@Autowired
	IncomeService incomeService;

	@Override
	public List<Coupon> getAllByCategory(String category) throws CouponSystemException {
		List<Coupon> coupons = couponsRepository.getAllByType(category);
		return coupons;
	}

	@Override
	public List<Coupon> getAll() throws CouponSystemException {
		List<Coupon> coupons = couponsRepository.findAll();
		return coupons;
	}

	@Override
	public Coupon getCouponById(long couponId) throws CouponSystemException {
		Coupon coupon = couponsRepository.findById(couponId);
		return coupon;
	}

	@Override
	public List<Coupon> getCustomerCoupons(long customerId) throws CouponSystemException {
		User user = userRepoitory.findById(customerId);
		List<Coupon> coupons = user.getCoupons();
		return coupons;
	}

	@Override
	public void createCoupon(Coupon coupon, String companyName) throws CouponSystemException {
		User user = userRepoitory.findByUsername(companyName);
		if (user != null) {
			System.out.println(coupon);
			ValidateBeansUtil.couponValidate(coupon);
			//System.out.println(couponsRepository.existsByTitle(coupon.getTitle()));
			if(couponsRepository.existsByTitle(coupon.getTitle())) {
				throw new CouponSystemException(ErrorType.TITLE_EXIST);
			}
			couponsRepository.save(coupon);
			Income income = this.createIncome(user.getUsername(), 100, IncomeType.COMPANY_NEW_COUPON);
			incomeService.storeIncome(income);
		} else {
			throw new CouponSystemException(ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);
		}

	}

	@Override
	public void updateCoupon(Coupon coupon, String companyName) throws CouponSystemException {

		User user = userRepoitory.findByUsername(companyName);
		if (user != null) {
			if (user.getId() == coupon.getCompanyId()) {
				System.out.println(coupon);
				couponsRepository.save(coupon);
				Income income = this.createIncome(user.getUsername(), 10, IncomeType.COMPANY_UPDATE_COUPON);
				incomeService.storeIncome(income);

			} else {
				throw new CouponSystemException(ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);
			}
		}

	}

	@Override
	public void updateAmount(long couponId, int amount) throws CouponSystemException {

		Coupon coupon = couponsRepository.findById(couponId);
		coupon.setAmount(amount);
		couponsRepository.save(coupon);
		// this.createIncome(name, amount, incomeType)
	}

	@Override
	public void deleteCoupon(long companyId, long couponId) throws CouponSystemException {

		couponsRepository.deleteById(couponId);
	}

	@Override
	public List<Coupon> getCompanyCoupons(long companyId) {

		List<Coupon> coupons = couponsRepository.getAllBycompanyId(companyId);
		return coupons;
	}

	@Override
	public List<Coupon> getCartCouponsFromBD(List<Long> CouponCart) {
		System.out.println("in getCartCouponsFromBD");
		List<Coupon> resCopuons = new ArrayList<>();
		for (long couponId : CouponCart) {

			resCopuons.add(couponsRepository.findById(couponId));
		}

		return resCopuons;
	}

	@Override
	public long purchaseCoupon(long customerId, List<Long> couponCart) {
		User user = userRepoitory.findById(customerId);
		if (user == null) {
			throw new CouponSystemException(ErrorType.CUSTOMER_NOT_EXISTS);
		}
		List<Coupon> couponsCart = new ArrayList<>();
		for (long copuonId : couponCart) {
			couponsCart.add(couponsRepository.findById(copuonId));
		}
		System.out.println("in purchase");
		for (Coupon coupon : couponsCart) {
			if (coupon.getAmount() > 0) {
				coupon.setAmount(coupon.getAmount() - 1);
			} else {
				throw new CouponSystemException(ErrorType.OUT_OFF_STOCK);
			}
		}
		couponsRepository.saveAll(couponsCart);
		user.addCoupons(couponsCart);
		userRepoitory.save(user);

		for (Coupon coupon : couponsCart) {
			Income income = this.createIncome(user.getUsername(), coupon.getPrice(), IncomeType.CUSTOMER_PURCHASE);
			incomeService.storeIncome(income);
		}

		return 1;
	}

	private Income createIncome(String name, double amount, IncomeType incomeType) {
		Income income = new Income();
		income.setName(name);
		income.setAmount(amount);
		income.setDate(new Date());
		income.setDescription(incomeType.getMessage());
		return income;

	}

	@Override
	public void removeExpiredCoupons(Date date) throws CouponSystemException {
		
		couponsRepository.removeExpiredCoupons(date);

	}

	@Override
	public List<Coupon> getCompanySoldCopuon(long companyId) throws CouponSystemException {
	
		return couponsRepository.findAllCompanySoldCopuons(companyId);
	}

}
