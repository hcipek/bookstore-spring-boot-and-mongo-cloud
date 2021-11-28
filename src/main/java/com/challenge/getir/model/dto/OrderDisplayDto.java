package com.challenge.getir.model.dto;

import com.challenge.getir.model.type.OrderStatus;
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
public class OrderDisplayDto {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("creation_date")
    private LocalDate creationDate;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("order_details")
    private List<OrderDetailDisplayDto> orderDetails;
    @JsonProperty("total_price")
    private Double totalPrice;
    @JsonProperty("order_status")
    private OrderStatus orderStatus;

}
