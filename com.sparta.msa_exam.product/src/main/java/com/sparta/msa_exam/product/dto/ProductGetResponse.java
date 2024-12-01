package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetResponse {
    private Long productId;
    private String name;
    private Integer supplyPrice;

    public static ProductGetResponse from(Product product) {
        return ProductGetResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .supplyPrice(product.getSupplyPrice())
                .build();
    }
}
