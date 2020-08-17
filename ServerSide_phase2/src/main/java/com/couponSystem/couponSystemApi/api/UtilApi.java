package com.couponSystem.couponSystemApi.api;



import com.couponSystem.couponSystemApi.enums.ClientType;
import com.couponSystem.couponSystemApi.enums.CouponType;
import com.couponSystem.couponSystemApi.enums.ErrorType;
import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;


public class UtilApi {

	
	public static CouponSystemException securityBreachException(String methodException) {
		return new CouponSystemException(methodException ,
				ErrorType.SECURITY_BREACH_ACCESS_NOT_ALLOWED);

	}
	
	public static CouponType checkCouponType(String type) throws CouponSystemException {
		try {
			// try parse string to clientType(enum)
			CouponType couponType = CouponType.valueOf(type);
			return couponType;
		} catch (Exception e) {
			// when the string illegal and impossible to parse the string
			throw new CouponSystemException("the coupon type you insert not allowed", ErrorType.ILLEGAL_TYPE);
		}

	}
	
	
	
	
}
