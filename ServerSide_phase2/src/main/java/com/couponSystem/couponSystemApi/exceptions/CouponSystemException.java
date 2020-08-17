package com.couponSystem.couponSystemApi.exceptions;

import java.sql.SQLException;

import com.couponSystem.couponSystemApi.enums.ErrorType;

public class CouponSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorType errorType;

	public CouponSystemException(ErrorType errorType) {
		this.errorType = errorType;

	}

	public CouponSystemException(String message, Throwable exception, ErrorType errorType) {
		super(message, exception);
		this.errorType = errorType;
	}

	public CouponSystemException(ErrorType errorType, Throwable exception) {
		super(exception);
		this.errorType = errorType;
	}

	public CouponSystemException(ErrorType errorType, SQLException sqlException) {
		super(sqlException);
		this.errorType = errorType;
	}

	public CouponSystemException(String message, ErrorType errorType) {
		super(message);
		this.errorType = errorType;

	}

	public ErrorType getErrorType() {
		return errorType;
	}

}
