package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRequest {
    private String name;
    private Integer supplyPrice;

    public Product toProduct() {
        return Product.builder()
                .name(name)
                .supplyPrice(supplyPrice)
                .build();
    }
}
