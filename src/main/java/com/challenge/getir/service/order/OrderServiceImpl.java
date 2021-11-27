package com.challenge.getir.service.order;

import com.challenge.getir.exception.EntityNotFoundException;
import com.challenge.getir.model.Order;
import com.challenge.getir.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Page<Order> findOrdersByCustomerId(Long customerId) {
        return findOrdersByCustomerId(customerId, 0, 20);
    }

    @Override
    public Page<Order> findOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        log.info("Getting orders of customer {} with page number {} and pageSize {}.", customerId, pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAllByCustomerId(pageable, customerId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId.toString())
                .orElseThrow(() -> new EntityNotFoundException("Order doesnt exists!"));
    }

    @Override
    public Page<Order> findOrdersByDateInterval(LocalDate startDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(0, 20);
        return orderRepository.findAllByCreationDateBetween(startDate, endDate, pageable);
    }
}
