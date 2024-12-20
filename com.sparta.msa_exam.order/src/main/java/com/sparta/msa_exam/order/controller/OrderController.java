package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderAddRequest;
import com.sparta.msa_exam.order.dto.OrderGetResponse;
import com.sparta.msa_exam.order.dto.OrderResponse;
import com.sparta.msa_exam.order.dto.OrderUpdateRequest;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderAddRequest request,
                                                     @RequestHeader(value = "X-User-Id", required = true) String userId) {
        OrderResponse response = orderService.createOrder(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long orderId,
                                                     @RequestBody OrderUpdateRequest request,
                                                     @RequestHeader(value = "X-User-Id", required = true) String userId) {
        OrderResponse response = orderService.updateOrder(request, orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderGetResponse> getOrder(@PathVariable Long orderId,
                                                     @RequestHeader(value = "X-User-Id", required = true) String userId){
        OrderGetResponse response = orderService.getOrder(orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
