package com.couponSystem.couponSystemApi.beans;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseBean{
	
//	private static final long serialVersionUID = 1L;
	private long[] coupons;
	private long customerId;
	
	public PurchaseBean() {
		
	}
	
	public PurchaseBean(long[] coupons, long customerId) {
		super();
		this.coupons = coupons;
		this.customerId = customerId;
	}

	
	public long[] getCoupons() {
		return coupons;
	}

	public void setCoupons(long[] coupons) {
		this.coupons = coupons;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
	
	
}
