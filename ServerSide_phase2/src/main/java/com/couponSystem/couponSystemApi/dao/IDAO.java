package com.couponSystem.couponSystemApi.dao;

import com.couponSystem.couponSystemApi.exceptions.CouponSystemException;

public interface IDAO<T> {

	long create(T t) throws CouponSystemException;

	T read(long id) throws CouponSystemException;

	void update(T t) throws CouponSystemException;

	void delete(long id) throws CouponSystemException;

	
	
	
}
