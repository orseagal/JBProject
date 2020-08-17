package com.couponSystem.couponSystemApi.core;


public class CouponSystem {

	private static CouponSystem instance;

	public static CouponSystem getInstanse() {

		if (instance == null) {

			instance = new CouponSystem();

		}

		return instance;
	}
	
	}

