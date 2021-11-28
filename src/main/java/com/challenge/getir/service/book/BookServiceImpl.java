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
        log.info("Trying to save book: {}", book.toString());
        book = bookRepository.save(book);
        log.info("book saved. {}", book.toString());
        return book;
    }

    @Override
    public Book stockUpdate(BookStockRequestDto bookStockRequestDto) {
        String bookId = bookStockRequestDto.getBookId();
        log.info("Trying to update stocks of book with id: {}, newStocks : {}", bookId, bookStockRequestDto.getNewStockSize());

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("This book does not exists!"));
        log.info("Book found : {}", book.toString());
        book.stockSize(bookStockRequestDto.getNewStockSize());

        book = save(book);
        log.info("Book saved with new stocks : {}", book.toString());
        return book;
    }

    @Override
    public Book findById(String bookId) {
        log.info("Trying to find book with id: {}", bookId);
        Book book = bookRepository.findById(bookId).orElseThrow(()->new EntityNotFoundException("Book doesnt exists!"));
        log.info("book found : {}", book.toString());
        return book;
    }
}
