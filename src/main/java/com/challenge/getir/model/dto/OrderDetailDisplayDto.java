package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDisplayDto {
    @JsonProperty("book_id")
    private String bookId;
    private Integer count;
    @JsonProperty("price_per_unit")
    private Double pricePerUnit;
    @JsonProperty("detail_total_price")
    private Double detailTotalPrice;
}