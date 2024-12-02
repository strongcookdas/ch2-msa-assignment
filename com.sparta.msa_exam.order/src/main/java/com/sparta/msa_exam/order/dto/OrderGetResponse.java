package com.sparta.msa_exam.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderGetResponse {
    private Long orderId;
    private List<Long> productIds;
}
