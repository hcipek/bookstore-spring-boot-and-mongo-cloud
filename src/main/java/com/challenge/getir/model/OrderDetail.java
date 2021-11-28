package com.challenge.getir.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class OrderDetail {

    private String bookId;

    private Integer count;

    private Double pricePerUnit;

    private Double detailTotalPrice;

    private OrderDetail() {

    }

    public static OrderDetail getInstance() {
        return new OrderDetail();
    }

    public OrderDetail bookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public OrderDetail count(int count) {
        this.count = count;
        calculateTotalPrice();
        return this;
    }

    public OrderDetail pricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        calculateTotalPrice();
        return this;
    }

    public OrderDetail calculateTotalPrice() {
        if (pricePerUnit != null && count != null
                && !pricePerUnit.equals(Double.valueOf(0)) && !this.count.equals(Integer.valueOf(0)))
            detailTotalPrice = BigDecimal.valueOf(pricePerUnit * count).setScale(2, RoundingMode.FLOOR).doubleValue();
        else
            detailTotalPrice = BigDecimal.valueOf(0).setScale(2, RoundingMode.CEILING).doubleValue();
        return this;
    }
}