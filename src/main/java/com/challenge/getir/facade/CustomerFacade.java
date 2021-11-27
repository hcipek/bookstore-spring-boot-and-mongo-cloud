package com.challenge.getir.facade;

import com.challenge.getir.mapping.CustomerMapper;
import com.challenge.getir.model.Customer;
import com.challenge.getir.model.dto.CustomerDto;
import com.challenge.getir.model.dto.OrderDto;
import com.challenge.getir.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomerFacade {

    private final OrderFacade orderFacade;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    public Page<OrderDto> getOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        return orderFacade.getOrdersByCustomerId(customerId, pageNumber, pageSize);
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.map(customerDto);
        customer = customerService.save(customer);
        CustomerDto createdCustomer = customerMapper.map(customer);
        return createdCustomer;
    }
}
