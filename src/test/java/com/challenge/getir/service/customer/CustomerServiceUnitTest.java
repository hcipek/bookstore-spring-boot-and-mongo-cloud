package com.challenge.getir.service.customer;

import com.challenge.getir.model.Customer;
import com.challenge.getir.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceUnitTest {

    CustomerServiceImpl customerService = null;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void saveCustomer_OK() {
        Customer customer = Customer.getInstance()
                .customerName("test")
                .id("testId")
                .password("pass")
                .email("email");
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.save(customer);

        assertEquals(savedCustomer, customer);
        assertEquals(customer.getCustomerName(), savedCustomer.getCustomerName());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
        assertEquals(customer.getPassword(), savedCustomer.getPassword());
        assertEquals(customer.getId(), savedCustomer.getId());
    }
}
