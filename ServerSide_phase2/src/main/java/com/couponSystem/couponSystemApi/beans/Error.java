package com.couponSystem.couponSystemApi.beans;

public class Error {

	private int internalErrorCode;
	private String internalErrorMessage;
	private String message;

	public Error() {
	}

	public Error(int internalErrorCode, String internalErrorMessage) {
		this.internalErrorCode = internalErrorCode;
		this.internalErrorMessage = internalErrorMessage;
	}

	public Error(int internalErrorCode, String internalErrorMessage, String message) {
		this.internalErrorCode = internalErrorCode;
		this.internalErrorMessage = internalErrorMessage;
		this.message = message;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public void setInternalErrorCode(int internalErrorCode) {
		this.internalErrorCode = internalErrorCode;
	}

	public String getInternalErrorMessage() {
		return internalErrorMessage;
	}

	public void setInternalErrorMessage(String internalErrorMessage) {
		this.internalErrorMessage = internalErrorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
