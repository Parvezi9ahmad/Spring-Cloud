package com.bharath.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.springcloud.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
	Coupon findByCode(String code);

}
