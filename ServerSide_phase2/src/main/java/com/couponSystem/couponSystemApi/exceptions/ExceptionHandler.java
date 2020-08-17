package com.couponSystem.couponSystemApi.exceptions;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.couponSystem.couponSystemApi.beans.Error;


@Provider
public class ExceptionHandler  implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {

		if (exception instanceof CouponSystemException) {
			CouponSystemException couponSystemException = (CouponSystemException) exception;
			if (couponSystemException.getErrorType().isLogDataBase() == true) {
				// save the error on date base or log to file
				
			}
			Error error = new Error(couponSystemException.getErrorType().getError()  , couponSystemException.getMessage() ,couponSystemException.getErrorType().getErrorClientMessage());
			System.out.println("message " + couponSystemException.getMessage());
			return Response.status(600)
					.entity(error).type(MediaType.APPLICATION_JSON).build();
		}
		System.out.println(exception.getMessage());
		exception.printStackTrace();
		return Response.status(800).entity("general error").build();

	}
}
