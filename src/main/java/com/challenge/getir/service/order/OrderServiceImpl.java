package com.challenge.getir.service.order;

import com.challenge.getir.exception.EntityNotFoundException;
import com.challenge.getir.model.Order;
import com.challenge.getir.model.type.OrderStatus;
import com.challenge.getir.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findCompletedOrdersByCustomerId(String customerId) {
        return orderRepository.findAllByCustomerIdAndStatus(customerId, OrderStatus.COMPLETE, Sort.by(Sort.Direction.DESC, "creationDate"));
    }

    @Override
    public Page<Order> findOrdersByCustomerId(String customerId, Integer pageNumber, Integer pageSize) {
        log.info("Getting orders of customer {} with page number {} and pageSize {}.", customerId, pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAllByCustomerId(customerId, pageable);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order completeOrder(Order order) {
        order.completeOrder();
        return save(order);
    }

    @Override
    public Order cancelOrder(Order order) {
        order.cancelOrder();
        return save(order);
    }

    @Override
    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order doesnt exists!"));
    }

    @Override
    public Page<Order> findOrdersByDateInterval(LocalDate startDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(0, 20);
        return orderRepository.findAllByCreationDateBetween(startDate, endDate, pageable);
    }
}
