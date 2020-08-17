package com.SecureApp.service;

import com.SecureApp.entity.Coupon;
import com.SecureApp.exceptionHander.CouponSystemException;

import java.util.Date;
import java.util.List;

public interface CouponService {

	public List<Coupon> getAllByCategory(String category) throws CouponSystemException;
	
	public List<Coupon> getAll() throws CouponSystemException;
	
	public Coupon getCouponById(long couponId) throws CouponSystemException;
	
	public List<Coupon> getCustomerCoupons(long customerId) throws CouponSystemException;
	
	public void createCoupon(Coupon coupon,String companyName) throws CouponSystemException;
	
	public void updateCoupon(Coupon coupon,String companyName) throws CouponSystemException;
	
	public void updateAmount(long couponId,int amount) throws CouponSystemException;
	
	public void deleteCoupon(long companyId,long couponId) throws CouponSystemException;
	
	public List<Coupon> getCompanyCoupons(long companyId) throws CouponSystemException;
	
	public List<Coupon> getCompanySoldCopuon(long companyId) throws CouponSystemException;
	
	public List<Coupon> getCartCouponsFromBD(List<Long> CouponCart) throws CouponSystemException;
	
	public long purchaseCoupon(long customerId,List<Long> couponCart) throws CouponSystemException;
	
	public void removeExpiredCoupons(Date date) throws CouponSystemException;
	
	
	
}
