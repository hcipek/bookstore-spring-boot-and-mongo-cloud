package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDto {
    @NotBlank
    @NotNull
    @JsonProperty("customer_id")
    private String customerId;
    @NotBlank
    @NotNull
    @JsonProperty("order_details")
    private List<OrderDetailCreateDto> orderDetails;
}
