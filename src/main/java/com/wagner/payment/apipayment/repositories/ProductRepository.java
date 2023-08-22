package com.wagner.payment.apipayment.repositories;

import com.wagner.payment.apipayment.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}