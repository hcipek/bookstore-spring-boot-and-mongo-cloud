package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticDto {
    private String month;
    @JsonProperty("total_order_count")
    private int totalOrderCount = 0;
    @JsonProperty("total_book_count")
    private int totalBookCount = 0;
    @JsonProperty("total_purchased_amount")
    private double totalPurchasedAmount = 0;

    public void incrementOrder () {
        totalOrderCount++;
    }

    public void addBook (int bookCount) {
        totalBookCount += bookCount;
    }

    public void addPurchaseAmount (double purchasedAmount) {
        totalPurchasedAmount = BigDecimal.valueOf(totalPurchasedAmount)
                .add(BigDecimal.valueOf(purchasedAmount)
                                .setScale(2, RoundingMode.FLOOR))
                .doubleValue();
    }
}