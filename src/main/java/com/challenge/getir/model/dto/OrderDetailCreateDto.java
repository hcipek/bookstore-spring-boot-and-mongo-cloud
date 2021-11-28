package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class OrderDetailCreateDto {
    @NotBlank
    @JsonProperty("book_id")
    private String bookId;
    @NotBlank
    private Integer count;
}