package com.challenge.getir.controller;

import com.challenge.getir.facade.CustomerFacade;
import com.challenge.getir.model.dto.CustomerDto;
import com.challenge.getir.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto ) {
        CustomerDto createdCustomer = customerFacade.createCustomer(customerDto);
        return ResponseEntity.accepted().body(customerDto);
    }

    @GetMapping("/{customer_id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<OrderDto>> getOrdersOfCustomer (@PathVariable("customer_id") Long customerId,
                                                               @RequestParam(value = "page_size", required = false, defaultValue = "0") Integer pageSize,
                                                               @RequestParam(value = "page_number", required = false, defaultValue = "20") Integer pageNumber) {
        Page<OrderDto> pageResult = customerFacade.getOrdersByCustomerId(customerId, pageNumber, pageSize);
        return ResponseEntity.ok(pageResult);
    }
}
