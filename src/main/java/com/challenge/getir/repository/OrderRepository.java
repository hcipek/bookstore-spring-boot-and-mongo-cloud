package com.challenge.getir.repository;

import com.challenge.getir.model.Order;
import com.challenge.getir.model.type.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByCustomerIdAndStatus(String customerId, OrderStatus status, Sort sort);
    Page<Order> findAllByCustomerId(String customerId, Pageable pageable);

    Page<Order> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
