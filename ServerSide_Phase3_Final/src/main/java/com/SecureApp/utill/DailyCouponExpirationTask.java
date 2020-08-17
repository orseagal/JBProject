//package com.SecureApp.utill;
//
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.example.SecureApp.exceptionHander.CouponSystemException;
//import com.example.SecureApp.service.CouponService;
//
//
///**
// * class {@link DailyCouponExpirationTask} responsible to delete all the coupon
// * expired from database
// * 
// */
//@Component
//public class DailyCouponExpirationTask implements Runnable {
//	
//	@Autowired
//	private CouponService couponService;
//	
//	
//	
//	private final static long TAMESLEEP = 1000 * 60 * 60 * 24; // one day
//	private boolean quit = true;
//	private	Thread thread;
//
//	public DailyCouponExpirationTask() {
//		thread = new Thread(this);
//		thread.start();
//	}
//	
//	
//	@Override
//	public void run() {
//	
//		while (quit) {
//			try { 
//				couponService.removeExpiredCoupons(new Date());
//				System.out.println("the DailyTask try/remove the expired coupon ");
//			} catch (CouponSystemException e1) {
//				e1.printStackTrace();
//			}
//			try {
//				Thread.sleep(TAMESLEEP);
//			} catch (InterruptedException e) {
//				break;
//			}
//		}
//
//		System.out.println("the end DailyTask quit = "+quit + "when the app shutdown");
//	}
//
//	/**
//	 * when the API shutdown this method will be stop the dailyCouponExpirationTask
//	 * task
//	 */
//	public void stopTask() {
//		this.quit=false;
//		this.thread.interrupt();
//	}
//	
//	public Thread getThread() {
//		return this.thread;
//	}
//
//}
