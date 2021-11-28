package com.challenge.getir.service.customer;

import com.challenge.getir.exception.BadRequestException;
import com.challenge.getir.model.Customer;
import com.challenge.getir.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        log.info("Trying to save customer: {}", customer.toString());
        if (customerRepository.existsByEmail(customer.getEmail())) {
            log.error("This email already exists! : {}", customer.getEmail());
            throw new BadRequestException("Email already exists");
        }
        customer = customerRepository.save(customer);
        log.info("Customer saved. {}", customer.toString());
        return customer;
    }

}
