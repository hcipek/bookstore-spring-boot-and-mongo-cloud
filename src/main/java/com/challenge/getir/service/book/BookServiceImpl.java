package com.challenge.getir.service.book;

import com.challenge.getir.exception.EntityNotFoundException;
import com.challenge.getir.model.Book;
import com.challenge.getir.model.dto.BookStockRequestDto;
import com.challenge.getir.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book stockUpdate(BookStockRequestDto bookStockRequestDto) {
        String bookId = bookStockRequestDto.getBookId();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("This book does not exists!"));
        book.stockSize(bookStockRequestDto.getNewStockSize());

        book = save(book);

        return book;
    }

    @Override
    public Book findById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->new EntityNotFoundException("Book doesnt exists!"));
    }
}
