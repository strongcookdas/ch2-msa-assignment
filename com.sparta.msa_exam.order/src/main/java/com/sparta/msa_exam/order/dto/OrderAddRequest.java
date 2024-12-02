package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.model.Order;
import com.sparta.msa_exam.order.model.OrderStatus;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddRequest {
    private List<Long> orderItemIds;

    public Order toOrder(UUID userId) {
        return Order.builder()
                .orderItemIds(orderItemIds)
                .status(OrderStatus.CREATED)
                .userId(userId)
                .build();
    }
}
