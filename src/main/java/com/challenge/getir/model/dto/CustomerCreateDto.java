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
public class CustomerCreateDto {
    @NotBlank
    @NotNull
    @JsonProperty("customer_name")
    private String customerName;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;

}
