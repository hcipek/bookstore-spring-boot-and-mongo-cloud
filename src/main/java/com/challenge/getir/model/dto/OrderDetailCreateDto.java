package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderDetailCreateDto {
    @NotBlank
    @NotNull
    @JsonProperty("book_id")
    private String bookId;
    @NotBlank
    @NotNull
    private Integer count;
}