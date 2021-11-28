package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookStockRequestDto {
    @NotBlank
    @NotNull
    @JsonProperty("book_id")
    private String bookId;
    @NotBlank
    @NotNull
    @JsonProperty("new_stock_size")
    private Integer newStockSize;
}
