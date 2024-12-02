package com.sparta.msa_exam.order.client.product;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products")
    List<ProductGetResponse> getProducts();
}
