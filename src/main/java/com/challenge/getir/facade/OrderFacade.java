package com.challenge.getir.facade;

import com.challenge.getir.exception.InsufficentStockException;
import com.challenge.getir.model.Order;
import com.challenge.getir.model.OrderDetail;
import com.challenge.getir.model.dto.OrderCreateDto;
import com.challenge.getir.model.dto.OrderDetailCreateDto;
import com.challenge.getir.model.dto.OrderDetailDisplayDto;
import com.challenge.getir.model.dto.OrderDisplayDto;
import com.challenge.getir.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderFacade {

    private final BookOrderStockFacade bookOrderStockFacade;
    private final OrderService orderService;

    public Page<OrderDisplayDto> getOrders(String customerId, Integer pageNumber, Integer pageSize) {
        Page<Order> pageResult = orderService.findOrdersByCustomerId(customerId, pageNumber, pageSize);
        log.info("Page result with total {} pages with total {} orders of customer with id : {}", pageResult.getTotalPages(), pageResult.getTotalElements(), customerId);
        return pageResult.map(order -> convert(order));
    }

    public List<OrderDisplayDto> getOrders(String customerId) {
        List<Order> orders = orderService.findCompletedOrdersByCustomerId(customerId);
        if (CollectionUtils.isEmpty(orders))
            return new ArrayList<>();
        return orders.stream()
                .map(o -> convert(o))
                .collect(Collectors.toList());
    }

    public OrderDisplayDto createOrder(OrderCreateDto orderCreateDto) {
        Order order = convert(orderCreateDto);

        order = orderService.save(order);

        OrderDisplayDto draftOrder = convert(order);

        try {
            draftOrder = bookOrderStockFacade.heldStocksByOrder(draftOrder);
            convert(order, draftOrder);
            order = orderService.completeOrder(order);
        } catch (InsufficentStockException e) {
            order = orderService.cancelOrder(order);
            throw e;
        } finally {
            OrderDisplayDto finishedOrder = convert(order);
            return finishedOrder;
        }
    }

    public OrderDisplayDto getOrderById(String orderId) {
        Order order = orderService.findOrderById(orderId);
        OrderDisplayDto requestedOrder = convert(order);
        return requestedOrder;
    }

    public Page<OrderDisplayDto> getOrdersByDateInterval(LocalDate startDate, LocalDate endDate) {
        Page<Order> pageResult = orderService.findOrdersByDateInterval(startDate, endDate);
        return pageResult.map(order -> convert(order));
    }

    private Order convert(OrderCreateDto orderCreateDto) {
        return Order.getInstance()
                .customerId(orderCreateDto.getCustomerId())
                .orderDetails(convertOrderDetailCreateDtos(orderCreateDto.getOrderDetails()));
    }

    private OrderDisplayDto convert(Order order) {
        return OrderDisplayDto.builder()
                .orderId(order.getId())
                .creationDate(order.getCreationDate())
                .totalPrice(order.getTotalPrice())
                .customerId(order.getCustomerId())
                .orderDetails(convertOrderDetails(order.getOrderDetails()))
                .orderStatus(order.getStatus())
                .build();
    }

    private List<OrderDetail> convertOrderDetailCreateDtos(List<OrderDetailCreateDto> orderDetailCreateDtos) {
        Function<OrderDetailCreateDto, OrderDetail> fx = x -> OrderDetail.getInstance()
                .bookId(x.getBookId())
                .count(x.getCount());

        return orderDetailCreateDtos.stream()
                .map(fx)
                .collect(Collectors.toList());
    }

    private List<OrderDetailDisplayDto> convertOrderDetails(List<OrderDetail> orderDetails) {
        Function<OrderDetail, OrderDetailDisplayDto> fx = x -> OrderDetailDisplayDto.builder()
                .detailTotalPrice(x.getDetailTotalPrice())
                .pricePerUnit(x.getPricePerUnit())
                .bookId(x.getBookId())
                .count(x.getCount())
                .build();

        return orderDetails.stream()
                .map(fx)
                .collect(Collectors.toList());
    }

    private void convert (Order order, OrderDisplayDto orderDisplayDto) {
        Function<OrderDetailDisplayDto, OrderDetail> fx = dto -> OrderDetail.getInstance()
                .bookId(dto.getBookId())
                .count(dto.getCount())
                .pricePerUnit(dto.getPricePerUnit());

        List<OrderDetail> orderDetails = orderDisplayDto.getOrderDetails().stream()
                .map(fx)
                .collect(Collectors.toList());

        order.orderDetails(orderDetails);
    }
}
