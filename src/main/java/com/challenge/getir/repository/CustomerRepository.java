package com.challenge.getir.repository;

import com.challenge.getir.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Set<String> findCustomerOrdersById(String id);

}
