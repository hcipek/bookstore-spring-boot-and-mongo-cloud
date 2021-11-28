package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDisplayDto {
    @JsonProperty("book_id")
    private String bookId;
    @JsonProperty("creation_date")
    private LocalDate creationDate;
    @JsonProperty("book_name")
    private String bookName;
    @JsonProperty("author_name")
    private String authorName;
    private Double price;
    @JsonProperty("stock_size")
    private Integer stockSize;
}
