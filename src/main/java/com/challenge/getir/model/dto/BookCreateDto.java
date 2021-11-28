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
public class BookCreateDto {
    @NotBlank
    @NotNull
    @JsonProperty("book_name")
    private String bookName;
    @NotBlank
    @NotNull
    @JsonProperty("author_name")
    private String authorName;
    @NotBlank
    @NotNull
    private Double price;
    @NotBlank
    @NotNull
    @JsonProperty("stock_size")
    private Integer stockSize;
}
