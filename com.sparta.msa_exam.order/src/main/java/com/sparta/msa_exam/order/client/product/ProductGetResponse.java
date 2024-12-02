package com.sparta.msa_exam.order.client.product;

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
}
