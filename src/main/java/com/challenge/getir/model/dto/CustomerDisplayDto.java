package com.challenge.getir.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDisplayDto {
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("creation_date")
    private LocalDate creationDate;
    @JsonProperty("customer_name")
    private String customerName;
    private String email;

}
