//package com.example.SecureApp.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Embeddable;
//
//@Embeddable
//public class CustomerCouponEmb implements Serializable {
//
//	private long cust_id;
//
//	private long coupon_id;
//
//	public CustomerCouponEmb() {
//
//	}
//
//	public CustomerCouponEmb(long cust_Id, long coupon_id) {
//		super();
//		this.cust_id = cust_Id;
//		this.coupon_id = coupon_id;
//	}
//
//	public long getCust_Id() {
//		return cust_id;
//	}
//
//	public void setCust_Id(long cust_Id) {
//		this.cust_id = cust_Id;
//	}
//
//	public long getCoupon_id() {
//		return coupon_id;
//	}
//
//	public void setCoupon_id(long coupon_id) {
//		this.coupon_id = coupon_id;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (coupon_id ^ (coupon_id >>> 32));
//		result = prime * result + (int) (cust_id ^ (cust_id >>> 32));
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CustomerCouponEmb other = (CustomerCouponEmb) obj;
//		if (coupon_id != other.coupon_id)
//			return false;
//		if (cust_id != other.cust_id)
//			return false;
//		return true;
//	}
//
//}
