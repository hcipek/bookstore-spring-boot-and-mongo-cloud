package com.challenge.getir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long customerId;

    private LocalDate creationDate;

    class OrderDetails {

    }
}
