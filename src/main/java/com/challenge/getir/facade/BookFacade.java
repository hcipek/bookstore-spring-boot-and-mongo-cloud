package com.challenge.getir.facade;

import com.challenge.getir.mapping.BookMapper;
import com.challenge.getir.model.Book;
import com.challenge.getir.model.dto.BookDto;
import com.challenge.getir.model.dto.BookStockRequestDto;
import com.challenge.getir.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class BookFacade {

    private final BookMapper bookMapper;
    private final BookService bookService;

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.map(bookDto);
        book = bookService.save(book);
        BookDto createdBook = bookMapper.map(book);
        return createdBook;
    }

    public BookDto stockUpdate(BookStockRequestDto bookStockRequestDto) {
        Book book = bookService.stockUpdate(bookStockRequestDto);
        BookDto updatedBook = bookMapper.map(book);
        return updatedBook;
    }
}
