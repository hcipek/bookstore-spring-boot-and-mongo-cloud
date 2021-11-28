package com.challenge.getir.model;

import com.challenge.getir.model.type.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Getter
@Document
public class Order {

    @Id
    private String id;

    private LocalDate creationDate = LocalDate.now();

    private String customerId;

    private List<OrderDetail> orderDetails;

    private Double totalPrice = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR).doubleValue();

    private OrderStatus status = OrderStatus.DRAFT;

    private Order() {

    }

    public static Order getInstance() {
        return new Order();
    }

    public Order customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Order orderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        calculateTotalPrice();
        return this;
    }

    public Order completeOrder () {
        status = OrderStatus.COMPLETE;
        return this;
    }

    public Order cancelOrder () {
        status = OrderStatus.CANCELLED;
        return this;
    }

    private Order calculateTotalPrice() {
        for (OrderDetail od : this.orderDetails)
            this.totalPrice += od.getDetailTotalPrice();
        return this;
    }
}
