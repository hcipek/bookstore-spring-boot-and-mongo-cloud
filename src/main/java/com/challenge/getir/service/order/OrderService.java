package com.challenge.getir.service.order;

import com.challenge.getir.model.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface OrderService {

    Page<Order> findOrdersByCustomerId (Long customerId);

    Page<Order> findOrdersByCustomerId (Long customerId, Integer pageNumber, Integer pageSize);

    Order save(Order order);

    Order findOrderById(Long orderId);

    Page<Order> findOrdersByDateInterval(LocalDate startDate, LocalDate endDate);
}
