package com.challenge.getir.facade;

import com.challenge.getir.model.dto.CustomerCreateDto;
import com.challenge.getir.model.dto.CustomerDisplayDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerFacadeIntegrationTest {

    @Autowired
    CustomerFacade customerFacade;

    static CustomerDisplayDto customerDisplayDto = new CustomerDisplayDto();

    @Test
    @Order(1)
    void saveBook_OK() {
        CustomerCreateDto customerCreateDto = CustomerCreateDto.builder()
                .customerName("test-customer")
                .email("test@test.com")
                .password("test")
                .build();

        customerDisplayDto = customerFacade.createCustomer(customerCreateDto);

        assertEquals(customerCreateDto.getCustomerName(), customerDisplayDto.getCustomerName());
        assertEquals(customerCreateDto.getEmail(), customerDisplayDto.getEmail());;
    }
}
