package com.SecureApp.entity;

public class PasswordBean {
	
	private long id;
	private String clientType;
	private String password;
	private String newPassword;
	
	
	public PasswordBean() {
	}
	
	public PasswordBean(long id, String clientType, String password, String newPassword) {
		super();
		this.id = id;
		this.clientType =clientType;
		this.password = password;
		this.newPassword = newPassword;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordBean [id=" + id + ", clientType=" + clientType + ", password=" + password + ", newPassword=" + newPassword
				+ "]";
	}

}
	
	

