package com.bharath.springcloud.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bharath.springcloud.Entity.Coupon;

@FeignClient("COUPON-SERVICE")
public interface CouponClient {

	@GetMapping("/couponapi/coupons/{code}")
	Coupon getCoupon(@PathVariable("code") String code);
}