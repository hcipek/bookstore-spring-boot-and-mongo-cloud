package com.challenge.getir.facade;

import com.challenge.getir.mapping.OrderMapper;
import com.challenge.getir.model.Order;
import com.challenge.getir.model.dto.OrderDto;
import com.challenge.getir.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderFacade {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public Page<OrderDto> getOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        Page<Order> pageResult = orderService.findOrdersByCustomerId(customerId, pageNumber, pageSize);
        log.info("Page result with total {} pages with total {} orders of customer with id : {}", pageResult.getTotalPages(), pageResult.getTotalElements(), customerId);
        return pageResult.map(order -> orderMapper.map(order));
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.map(orderDto);
        order = orderService.save(order);
        OrderDto createdOrder = orderMapper.map(order);
        return createdOrder;
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderService.findOrderById(orderId);
        OrderDto requestedOrder = orderMapper.map(order);
        return requestedOrder;
    }

    public Page<OrderDto> getOrdersByDateInterval(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Page<Order> pageResult = orderService.findOrdersByDateInterval(startDate, endDate);
        return pageResult.map(order -> orderMapper.map(order));
    }
}
