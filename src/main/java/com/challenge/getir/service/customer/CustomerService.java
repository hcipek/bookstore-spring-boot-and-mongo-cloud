package com.challenge.getir.service.customer;

import com.challenge.getir.model.Customer;
import com.challenge.getir.model.Order;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Customer save(Customer customer);
}
