package com.challenge.getir.repository;

import com.challenge.getir.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findAllByCustomerId(Pageable pageable, Long customerId);

    Page<Order> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
