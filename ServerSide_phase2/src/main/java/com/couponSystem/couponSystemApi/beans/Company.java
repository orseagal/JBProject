package com.couponSystem.couponSystemApi.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * this class represent the company beans in the API this beans matching with
 * the company table
 * 
 */
@XmlRootElement
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String password;
	private String email;
	private String type="COMPANY";
	private Collection<Coupon> coupons;

	
	
	
	public Company() {
		super();
		setType(type);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		return result;
	}

	@Override
	public String toString() {
	
		return "Company [id = " + id + ", compName= " + name +", password= " + password + ", email= " + email + ", coupons= "
				+ coupons + ", type=" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
				return false;
	
		return true;
	}
	
	
	
	}
	
	

