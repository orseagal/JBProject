package com.SecureApp.rest;

import com.SecureApp.dao.ImageRepository;
import com.SecureApp.dao.UserRepositry;
import com.SecureApp.entity.Coupon;
import com.SecureApp.entity.ImageModel;
import com.SecureApp.entity.PasswordBean;
import com.SecureApp.entity.User;
import com.SecureApp.exceptionHander.CouponSystemException;
import com.SecureApp.service.CouponService;
import com.SecureApp.service.UserService;
import com.SecureApp.utill.ImageUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	@Autowired
	UserService userService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserRepositry userRepositry; 
	@Autowired
	ImageRepository imageRepository;
	
	@PutMapping("/update-password")
	public void updateCustomerPassword(@AuthenticationPrincipal final UserDetails userdetails ,@RequestBody PasswordBean passwordBean) throws CouponSystemException{
		User user =userRepositry.findByUsername(userdetails.getUsername());
		userService.updateCustomerPassword(passwordBean,user);
	}

	@GetMapping("/customerCoupons/{customerId}")
	public List<Coupon> getCustomerCoupons(@PathVariable long customerId) {

		return couponService.getCustomerCoupons(customerId);

	}

	@PostMapping("/cartCoupons")
	public List<Coupon> getCartCouponsFromBD(@RequestBody List<Long> CouponCart) throws CouponSystemException{
	
		return couponService.getCartCouponsFromBD(CouponCart);
	}

	@PostMapping("/purchase/{customerId}")
	public long purchaseCoupon(@PathVariable long customerId, @RequestBody List<Long> couponCart) throws CouponSystemException{

		return couponService.purchaseCoupon(customerId, couponCart);
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestBody MultipartFile file) throws IOException{
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageModel image = new ImageModel(file.getOriginalFilename(),file.getContentType(),ImageUtill.compressBytes(file.getBytes()));
		this.imageRepository.save(image);
	}
	
	@GetMapping("/getImage/{imageName}")
	public ImageModel ImageModel(@PathVariable("imageName") String name) {
		
		 final Optional<ImageModel> retrivedImage = imageRepository.findByName(name);
		 ImageModel image = new ImageModel(retrivedImage.get().getName(),retrivedImage.get().getType(),ImageUtill.decompressBytes(retrivedImage.get().getPicByte()));
		 return image;
	}
}
