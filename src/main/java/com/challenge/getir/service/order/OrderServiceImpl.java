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
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findCompletedOrdersByCustomerId(String customerId) {
        log.info("Trying find orders of customerId : {}", customerId);
        List<Order> orders = orderRepository.findAllByCustomerIdAndStatus(customerId, OrderStatus.COMPLETE, Sort.by(Sort.Direction.DESC, "creationDate"));
        log.info("Orders found with size : {}", CollectionUtils.isEmpty(orders) ? 0 : orders.size());
        return orders;
    }

    @Override
    public Page<Order> findOrdersByCustomerId(String customerId, Integer pageNumber, Integer pageSize) {
        log.info("Getting orders of customer {} with page number {} and pageSize {}.", customerId, pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Order> page = orderRepository.findAllByCustomerId(customerId, pageable);
        log.info("Page returned with total pages : {}, total elements : {}", page.getTotalPages(), page.getTotalElements());
        return page;
    }

    @Override
    public Order save(Order order) {
        log.info("Trying to save order: {}", order.toString());
        order = orderRepository.save(order);
        log.info("Order saved. {}", order.toString());
        return order;
    }

    @Override
    public Order completeOrder(Order order) {
        log.info("Trying to complete order: {}", order.toString());
        order.completeOrder();
        return save(order);
    }

    @Override
    public Order cancelOrder(Order order) {
        log.info("Trying to cancel order: {}", order.toString());
        order.cancelOrder();
        return save(order);
    }

    @Override
    public Order findOrderById(String orderId) {
        log.info("Trying to find order with id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order doesnt exists!"));
        log.info("Order found: {}", order.toString());
        return order;
    }

    @Override
    public Page<Order> findOrdersByDateInterval(LocalDate startDate, LocalDate endDate) {
        log.info("Getting orders between {} and {}.", startDate, endDate);
        Pageable pageable = PageRequest.of(0, 20);
        Page<Order> page = orderRepository.findAllByCreationDateBetween(startDate, endDate, pageable);
        log.info("Page returned with total pages : {}, total elements : {}", page.getTotalPages(), page.getTotalElements());
        return page;
    }
}
