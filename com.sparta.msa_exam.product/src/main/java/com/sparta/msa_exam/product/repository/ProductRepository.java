package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
