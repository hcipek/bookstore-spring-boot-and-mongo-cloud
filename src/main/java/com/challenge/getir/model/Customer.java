package com.challenge.getir.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
public class Customer {

    @Id
    private String id;

    private LocalDate creationDate = LocalDate.now();

    private String customerName;

    private Customer() {

    }

    public static Customer getInstance() {
        return new Customer();
    }

    public Customer customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }
}
