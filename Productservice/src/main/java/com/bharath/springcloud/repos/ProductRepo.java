package com.bharath.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.springcloud.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
