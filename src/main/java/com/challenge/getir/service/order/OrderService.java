package com.challenge.getir.service.order;

import com.challenge.getir.model.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<Order> findCompletedOrdersByCustomerId (String customerId);

    Page<Order> findOrdersByCustomerId (String customerId, Integer pageSize, Integer pageNumber);

    Order save(Order order);

    Order completeOrder (Order order);

    Order cancelOrder (Order order);

    Order findOrderById(String orderId);

    Page<Order> findOrdersByDateInterval(LocalDate startDate, LocalDate endDate);
}
