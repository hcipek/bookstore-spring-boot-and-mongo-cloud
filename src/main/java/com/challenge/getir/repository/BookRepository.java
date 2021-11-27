package com.challenge.getir.repository;

import com.challenge.getir.model.Book;
import com.challenge.getir.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
