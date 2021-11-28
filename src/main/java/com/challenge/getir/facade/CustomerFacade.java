package com.challenge.getir.facade;

import com.challenge.getir.model.Customer;
import com.challenge.getir.model.dto.CustomerCreateDto;
import com.challenge.getir.model.dto.CustomerDisplayDto;
import com.challenge.getir.model.dto.OrderDisplayDto;
import com.challenge.getir.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomerFacade {

    private final OrderFacade orderFacade;
    private final CustomerService customerService;
    private final BasicPasswordEncryptor encryptor;

    public Page<OrderDisplayDto> getOrdersByCustomerId(String customerId, Integer pageNumber, Integer pageSize) {
        return orderFacade.getOrders(customerId, pageNumber, pageSize);
    }

    public CustomerDisplayDto createCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = convert(customerCreateDto);
        customer = customerService.save(customer);
        CustomerDisplayDto createdCustomer = convert(customer);
        return createdCustomer;
    }

    private CustomerDisplayDto convert(Customer customer) {
        return CustomerDisplayDto.builder()
                .customerId(customer.getId())
                .creationDate(customer.getCreationDate())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .build();
    }

    private Customer convert(CustomerCreateDto customerCreateDto) {
        return Customer.getInstance()
                .customerName(customerCreateDto.getCustomerName())
                .password(encryptor.encryptPassword(customerCreateDto.getPassword()))
                .email(customerCreateDto.getEmail());
    }

}
