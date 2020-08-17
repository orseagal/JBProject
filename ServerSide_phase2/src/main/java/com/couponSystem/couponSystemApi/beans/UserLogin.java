package com.couponSystem.couponSystemApi.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserLogin {

	private String name;
	private Long id;
	private String password;
	private String email;
	private String type;
	private boolean rememberMe;

	public UserLogin() {
		super();
	}

	public boolean isRememberMe() {
		return rememberMe;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserLogin [name=" + name + ", id=" + id + ", password=" + password + ", email=" + email + ", type="
				+ type + ", rememberMe=" + rememberMe + "]";
	}



}
