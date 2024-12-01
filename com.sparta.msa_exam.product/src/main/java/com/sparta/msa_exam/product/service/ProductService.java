package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.dto.ProductGetResponse;
import com.sparta.msa_exam.product.model.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<ProductGetResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductGetResponse::from).collect(Collectors.toList());
    }
}
