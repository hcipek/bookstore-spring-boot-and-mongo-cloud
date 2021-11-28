package com.challenge.getir.service.order;

import com.challenge.getir.exception.EntityNotFoundException;
import com.challenge.getir.model.Book;
import com.challenge.getir.model.Order;
import com.challenge.getir.model.OrderDetail;
import com.challenge.getir.model.type.OrderStatus;
import com.challenge.getir.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    OrderServiceImpl orderService = null;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void saveOrder_OK() {
        Order order = testOrder();
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderService.save(order);

        assertEquals(savedOrder, order);
        assertEquals(savedOrder.getOrderDetails(), order.getOrderDetails());

        OrderDetail savedDetail = savedOrder.getOrderDetails().get(0);
        OrderDetail detail = order.getOrderDetails().get(0);

        assertEquals(detail.getBookId(), savedDetail.getBookId());
        assertEquals(detail.getPricePerUnit(), savedDetail.getPricePerUnit());
        assertEquals(detail.getCount(), savedDetail.getCount());
    }

    @Test
    void findOrderById_OK() {
        Order order = testOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Order foundOrder = orderService.findOrderById(order.getId());

        assertEquals(foundOrder, order);
    }

    @Test
    void findOrderById_NOT_OK() {
        Order order = testOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
        Executable executable = () -> orderService.findOrderById(order.getId());
        assertThrowsExactly(EntityNotFoundException.class, executable::execute, "Order doesnt exists!");
    }

    @Test
    void findOrdersByCustomerId_OK() {
        Order order = testOrder();
        Page<Order> emptyPage = Page.empty();
        Pageable pageable = PageRequest.of(0, 20);
        when(orderRepository.findAllByCustomerId(order.getCustomerId(), PageRequest.of(0, 20))).thenReturn(emptyPage);
        Page<Order> foundPage = orderService.findOrdersByCustomerId(order.getCustomerId(), pageable.getPageNumber(), pageable.getPageSize());

        assertEquals(foundPage, emptyPage);
    }

    @Test
    void findCompletedOrdersByCustomerId_OK() {
        Order order = testOrder();
        List<Order> orders = Collections.singletonList(order);
        when(orderRepository.findAllByCustomerIdAndStatus(order.getCustomerId(), OrderStatus.COMPLETE,
                Sort.by(Sort.Direction.DESC, "creationDate"))).thenReturn(orders);
        List<Order> foundOrders = orderService.findCompletedOrdersByCustomerId(order.getCustomerId());

        assertEquals(foundOrders, orders);
    }

    @Test
    void findOrdersByDateInterval_OK() {
        LocalDate localDate = LocalDate.now();
        LocalDate exDate = localDate.minusDays(1);
        Page<Order> emptyPage = Page.empty();
        Pageable pageable = PageRequest.of(0, 20);
        when(orderRepository.findAllByCreationDateBetween(exDate, localDate, PageRequest.of(0, 20))).thenReturn(emptyPage);
        Page<Order> foundPage = orderService.findOrdersByDateInterval(exDate, localDate);

        assertEquals(foundPage, emptyPage);
    }

    @Test
    void completeOrder_OK() {
        Order order = testOrder();
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderService.completeOrder(order);
        order.completeOrder();

        assertEquals(savedOrder, order);
    }

    @Test
    void cancelOrder_OK() {
        Order order = testOrder();
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderService.cancelOrder(order);
        order.cancelOrder();

        assertEquals(savedOrder, order);
    }

    private Order testOrder () {
        OrderDetail orderDetail = OrderDetail.getInstance()
                .bookId(testOrderBook().getId())
                .count(1)
                .pricePerUnit(testOrderBook().getPrice());
        return Order.getInstance()
                .customerId("test-customer-id")
                .id("test-order-id")
                .orderDetails(Collections.singletonList(orderDetail));
    }

    private Book testOrderBook () {
        return Book.getInstance()
                .id("test-book-id")
                .price(9.99)
                .stockSize(10)
                .authorName("test-author");
    }
}
