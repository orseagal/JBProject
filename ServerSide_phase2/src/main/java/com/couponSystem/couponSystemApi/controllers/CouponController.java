package com.couponSystem.couponSystemApi.controllers;

import java.util.Collection;
import java.util.Set;

import com.couponSystem.couponSystemApi.beans.Coupon;
import com.couponSystem.couponSystemApi.dao.ICouponDAO;
import com.couponSystem.couponSystemApi.dao.sql.CouponDBDAO;
import com.couponSystem.couponSystemApi.enums.CouponType;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;



public class CouponController {

	ICouponDAO couponDAO;

	public CouponController() throws CouponSystemException {

		this.couponDAO = new CouponDBDAO();

	}

	public long createCoupon(Coupon coupon) throws CouponSystemException {

		ValidateBeansUtil.couponValidate(coupon);

		if (couponDAO.existByTitle(coupon.getTitle())) {

			throw new CouponSystemException(ErrorType.TITLE_EXIST);

		}

		return couponDAO.create(coupon);

	}

//	public void purchaseCoupon(long customerId, long couponId) throws CouponSystemException {
//
//		couponDAO.updateAmount(couponId, -1);
//
//		Coupon coupon = couponDAO.read(couponId);
//
//		if (coupon != null) {
//
//			Collection<Coupon> customerCoupons = couponDAO.getCustomerCoupons(customerId);
//
//			if (customerCoupons.contains(coupon)) {
//
//				couponDAO.updateAmount(couponId, 1);
//
//				throw new CouponSystemException(ErrorType.PURCHASE_COUPON);
//			}
//			couponDAO.purchaseCoupon(customerId, couponId);
//		} else {
//
//			throw new CouponSystemException(ErrorType.COUPON_NOT_EXISTS);
//		}
//	}

	
	public void purchaseCoupon(long customerId, Set<Long> couponsCart) throws CouponSystemException {

		for(long couponCart : couponsCart ) {
			
			couponDAO.updateAmount(couponCart, -1);

			Coupon coupon = couponDAO.read(couponCart);

			if (coupon != null) {

				Collection<Coupon> customerCoupons = couponDAO.getCustomerCoupons(customerId);

				if (customerCoupons.contains(coupon)) {

					couponDAO.updateAmount(couponCart, 1);
								
					throw new CouponSystemException(ErrorType.PURCHASE_COUPON);
				}
				couponDAO.purchaseCoupon(customerId, couponCart);
			} else {

				throw new CouponSystemException(ErrorType.COUPON_NOT_EXISTS);
			}
			
			
			
		}
		
		
	}
	
	
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		ValidateBeansUtil.couponValidate(coupon);

		Coupon couponUpdate = couponDAO.read(coupon.getId());

		if (couponUpdate != null) {

			if (couponUpdate.equals(coupon)) {

				couponUpdate.setStartDate(coupon.getStartDate());
				couponUpdate.setEndDate(coupon.getEndDate());

				couponDAO.update(couponUpdate);
				return;
			} else {

				throw new CouponSystemException(ErrorType.UPDATE_COUPON_NOT_MATCHING);
			}

		} else {

			throw new CouponSystemException(ErrorType.COUPON_NOT_EXISTS);
		}

	}

	public Coupon getCoupon(long couponId) throws CouponSystemException {

		Coupon coupon = couponDAO.read(couponId);

		if (coupon == null) {

			throw new CouponSystemException(ErrorType.COUPON_NOT_EXISTS);
		}

		return coupon;

	}

	public Collection<Coupon> getAllCoupons() throws CouponSystemException {

		return couponDAO.getAllCoupons();
	}

	public Collection<Coupon> getCustomerCoupons(long customerId) throws CouponSystemException {

		return couponDAO.getCustomerCoupons(customerId);
	}

	public Collection<Coupon> getCustomerCouponsbyType(long customerId, CouponType type) throws CouponSystemException {

		return couponDAO.getCustomerCouponsByType(type, customerId);
	}

	public Collection<Coupon> getCustomerCouponsbyPrice(double price, long customerId) throws CouponSystemException {

		return couponDAO.getCustomerCouponsByPrice(price, customerId);
	}

	public Collection<Coupon> getCompanyCoupons(long companyId) throws CouponSystemException {

		return couponDAO.getCompanyCoupons(companyId);

	}

	public Collection<Coupon> getCompanyCouponsByPrice(long companyId, double price) throws CouponSystemException {

		return couponDAO.getCompanyCouponsByPrice(companyId, price);
	}

	public Collection<Coupon> getCompanyCouponsByType(long companyId, CouponType type) throws CouponSystemException {

		return couponDAO.getCompanyCouponsByType(type, companyId);
	}
	
	public Collection<Coupon> getCouponsByType(CouponType type) throws CouponSystemException {

		return couponDAO.getCouponsByType(type);
	}
	

	public Collection<Coupon> getCompanyCouponsByDate(long companyId, java.util.Date date)
			throws CouponSystemException {

		return couponDAO.getCompanyCouponsByDate(date, companyId);

	}

	public void removeCompanyCouponsByCoupon(long couponId, long companyId) throws CouponSystemException {

		Coupon coupon = couponDAO.read(couponId);

		if (coupon != null) {

			Collection<Coupon> coupons = couponDAO.getCompanyCoupons(companyId);

			if (coupons.contains(coupon)) {

				couponDAO.removeCustomerCouponByCoupon(coupon.getId());

				couponDAO.delete(coupon.getId());
			} else {

				throw new CouponSystemException("Comapny id: " + companyId,
						ErrorType.UPDATE_OR_REMOVE_BY_COMPANY_NOT_CREATE_THIS_COUPON);

			}
		} else {

			throw new CouponSystemException(ErrorType.ID_NOT_EXISTS);
		}

	}

	
	
	
}
