package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStockRequestDto {
    @JsonProperty("book_id")
    private Long bookId;
    @JsonProperty("new_stock_size")
    private Integer newStockSize;
}
