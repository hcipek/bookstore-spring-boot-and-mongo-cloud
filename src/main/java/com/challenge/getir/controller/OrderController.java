package com.challenge.getir.controller;

import com.challenge.getir.facade.OrderFacade;
import com.challenge.getir.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<OrderDto> createOrder (@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderFacade.createOrder(orderDto);
        return ResponseEntity.accepted().body(createdOrder);
    }

    @GetMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> getOrderById (@PathVariable("order_id") Long orderId) {
        OrderDto requestedOrder = orderFacade.getOrderById(orderId);
        return ResponseEntity.ok(requestedOrder);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<OrderDto>> getOrders (@RequestParam(value = "interval", required = false, defaultValue = "ascending") String interval) {
        Page<OrderDto> requestedOrders = orderFacade.getOrdersByDateInterval(interval);
        return ResponseEntity.ok(requestedOrders);
    }
}
