package com.SecureApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureAppApplication {
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SecureAppApplication.class, args);
		//DailyCouponExpirationTask task= new DailyCouponExpirationTask(); 
	}

}
