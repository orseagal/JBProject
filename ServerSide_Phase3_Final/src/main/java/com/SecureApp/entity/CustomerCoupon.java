package com.SecureApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_coupon")
public class CustomerCoupon {

	@Id
	@Column(name = "cust_id")
	private long customerId;

	@Column(name = "coupon_id")
	private long couponId;

	public CustomerCoupon() {
		super();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

}
