package com.SecureApp.dao;

import com.SecureApp.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true")
public interface CouponRepositry extends JpaRepository<Coupon, Long> {

	List<Coupon> getAllByType(String type);
	
	List<Coupon> getAllBycompanyId(long companyId);
	
	Coupon findById(long id);

	boolean existsByTitle(String title);
	//void save(List<Coupon> coupons);
	
	@Query("DELETE FROM Coupon WHERE end_date<:date")
	void removeExpiredCoupons(@Param("date") Date date);
	
	//SELECT * FROM coupons INNER JOIN customer_coupon ON coupons.id = customer_coupon.coupon_id WHERE coupons.comp_id =34

	@Query(value = "SELECT * FROM coupons INNER JOIN customer_coupon ON coupons.id = customer_coupon.coupon_id WHERE coupons.comp_id= :comapnyId", nativeQuery = true)
	List<Coupon> findAllCompanySoldCopuons(@Param("comapnyId") long companyId);

//	@Query("SELECT p FROM Coupon JOIN f.film_actors fa\r\n JOIN fa.actor ac\r\n WHERE ac.id =:comapnyId")
//	List<Coupon> findAllCompanySoldCopuons(@Param("comapnyId") long companyId);
}

