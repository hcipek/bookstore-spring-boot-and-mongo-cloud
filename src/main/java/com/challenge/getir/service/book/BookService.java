package com.challenge.getir.service.book;

import com.challenge.getir.model.Book;
import com.challenge.getir.model.dto.BookStockRequestDto;

public interface BookService {

    Book save(Book book);

    Book stockUpdate(BookStockRequestDto bookStockRequestDto);

    Book findById(String bookId);
}
