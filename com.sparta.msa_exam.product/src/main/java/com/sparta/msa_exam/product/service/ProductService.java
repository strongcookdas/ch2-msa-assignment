package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.model.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductAddResponse createProduct(ProductAddRequest request) {
        Product product = productRepository.save(request.toProduct());
        return new ProductAddResponse(product.getId());
    }
}
