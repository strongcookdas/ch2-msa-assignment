package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductAddRequest;
import com.sparta.msa_exam.product.dto.ProductAddResponse;
import com.sparta.msa_exam.product.dto.ProductGetResponse;
import com.sparta.msa_exam.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductAddResponse> createProduct(@RequestBody ProductAddRequest request,
                                                            @RequestHeader(value = "X-User-Id", required = true) String userId) {
        log.info("{}",userId);
        ProductAddResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductGetResponse>> getProducts(@RequestHeader(value = "X-User-Id", required = true) String userId){
        List<ProductGetResponse> response = productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
