package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.product.ProductClient;
import com.sparta.msa_exam.order.client.product.ProductGetResponse;
import com.sparta.msa_exam.order.dto.OrderAddRequest;
import com.sparta.msa_exam.order.dto.OrderAddResponse;
import com.sparta.msa_exam.order.model.Order;
import com.sparta.msa_exam.order.repository.OrderRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductClient productClient;

    public OrderAddResponse createOrder(OrderAddRequest request) {
        // 1. 상품 서비스에서 상품 목록 조회
        List<ProductGetResponse> products = productClient.getProducts();

        // 2. 유효한 상품 ID 필터링
        Set<Long> validProductIds = products.stream()
                .map(ProductGetResponse::getProductId) // 상품 ID 추출
                .collect(Collectors.toSet());

        List<Long> validOrderItemIds = request.getOrderItemIds().stream()
                .filter(validProductIds::contains) // 유효한 상품만 필터링
                .collect(Collectors.toList());

        // 3. 유효한 상품 ID가 없으면 예외 처리 또는 빈 주문 생성
        if (validOrderItemIds.isEmpty()) {
            throw new IllegalArgumentException("No valid products in the order");
        }

        // 4. 주문 생성 및 저장
        Order order = orderRepository.save(request.toOrder(UUID.randomUUID()));

        // 5. 응답 반환
        return new OrderAddResponse(order.getId());
    }
}
