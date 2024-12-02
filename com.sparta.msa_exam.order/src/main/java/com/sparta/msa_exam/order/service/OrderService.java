package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.product.ProductClient;
import com.sparta.msa_exam.order.client.product.ProductGetResponse;
import com.sparta.msa_exam.order.dto.OrderAddRequest;
import com.sparta.msa_exam.order.dto.OrderResponse;
import com.sparta.msa_exam.order.dto.OrderUpdateRequest;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductClient productClient;

    @Transactional
    public OrderResponse createOrder(OrderAddRequest request) {
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
        return new OrderResponse(order.getId());
    }

    @Transactional
    public OrderResponse updateOrder(OrderUpdateRequest request, Long orderId) {
        // 1. 주문 유효성
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No valid order"));
        // 2. 상품 서비스 상품 목록 조회
        List<ProductGetResponse> products = productClient.getProducts();

        // 3. 유효한 상품 ID 필터링
        Set<Long> validProductIds = products.stream()
                .map(ProductGetResponse::getProductId) // 상품 ID 추출
                .collect(Collectors.toSet());

        // 4. 상품 유효성 검사
        if (!validProductIds.contains(request.getProductId())) {
            throw new IllegalArgumentException("No valid products in the order");
        }

        // 5. 주문 수정
        order.update(request.getProductId());
        Order updateOrder = orderRepository.save(order);

        return new OrderResponse(updateOrder.getId());
    }
}
