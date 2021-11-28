package com.challenge.getir.model;

import com.challenge.getir.model.type.RoleType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@ToString
public class Customer {

    @Id
    private String id;

    private LocalDate creationDate = LocalDate.now();

    private String customerName;

    private String email;

    private String password;

    private RoleType role = RoleType.CUSTOMER;

    private Customer() {

    }

    public static Customer getInstance() {
        return new Customer();
    }

    public Customer id(String id) {
        this.id = id;
        return this;
    }

    public Customer customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public Customer password(String password) {
        this.password = password;
        return this;
    }
}
