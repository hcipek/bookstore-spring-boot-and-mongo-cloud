package com.challenge.getir.service.customer;

import com.challenge.getir.model.Customer;
import com.challenge.getir.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BasicPasswordEncryptor encryptor;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    private boolean checkPassword (String plain, String encrypted) {
        return encryptor.checkPassword(plain, encrypted);
    }
}
