package com.bharath.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.springcloud.Entity.Coupon;
import com.bharath.springcloud.Entity.Product;
import com.bharath.springcloud.repos.ProductRepo;
import com.bharath.springcloud.restClients.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/productapi")
@RefreshScope
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private CouponClient couponClient;
	
	@Value("${com.parvez.springcloud.prop}")
	private String prop;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@Retry(name = "product-api", fallbackMethod = "handleError")
	public Product create(@RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);

	}

	@RequestMapping(value = "/prop", method = RequestMethod.GET)
	public String getProp() {
		return this.prop;
	}

	public Product handleError(Product product, Exception exception) {
		System.out.println("Inside handle error");
		return product;
	}

}
