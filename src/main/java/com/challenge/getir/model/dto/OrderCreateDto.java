package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDto {
    @NotBlank
    @JsonProperty("customer_id")
    private String customerId;
    @NotBlank
    @JsonProperty("order_details")
    private List<OrderDetailCreateDto> orderDetails;
}
