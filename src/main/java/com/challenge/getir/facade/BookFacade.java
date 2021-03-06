package com.challenge.getir.facade;

import com.challenge.getir.exception.BadRequestException;
import com.challenge.getir.model.Book;
import com.challenge.getir.model.dto.*;
import com.challenge.getir.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class BookFacade {

    private final BookService bookService;

    public BookDisplayDto createBook(BookCreateDto bookCreateDto) {
        log.info("Creating book started : {}", bookCreateDto.toString());
        Book book = convert(bookCreateDto);
        validateBook(book);
        book = save(book);
        BookDisplayDto createdBook = convert(book);
        return createdBook;
    }

    public BookDisplayDto stockUpdate(BookStockRequestDto bookStockRequestDto) {
        log.info("Updating stocks of a book started wtih book Id : {}, new Stocks : {}", bookStockRequestDto.getBookId(), bookStockRequestDto.getNewStockSize());
        checkStocks(bookStockRequestDto.getNewStockSize());
        Book book = bookService.stockUpdate(bookStockRequestDto);
        BookDisplayDto updatedBook = convert(book);
        return updatedBook;
    }

    public BookDisplayDto getById (String bookId) {
        log.info("Get book started with id : {}", bookId);
        Book book = findById(bookId);
        return convert(book);
    }

    public BookDisplayDto heldStocks (String bookId, String orderId, Integer heldSize) {
        log.info("Helding stocks of book started with bookId : {}, orderId : {}, heldSize : {}", bookId, orderId, heldSize);
        Book book = findById(bookId);
        book.heldStocks(orderId, heldSize);
        book = save(book);
        return convert(book);
    }

    public void releaseStocks (List<String> bookIds, String orderId) {
        bookIds.forEach(bookId -> releaseStocks(bookId, orderId));
    }

    public BookDisplayDto releaseStocks (String bookId, String orderId) {
        log.info("releasing stocks of book : {}, from order : {}", bookId, orderId);
        return refreshHeldStocks(bookId, orderId, false);
    }

    public void returnStocks (List<String> bookIds, String orderId) {
         bookIds.forEach(bookId -> returnStocks(bookId, orderId));
    }

    public BookDisplayDto returnStocks (String bookId, String orderId) {
        log.info("return stocks of book : {}, from order : {}", bookId, orderId);
        return refreshHeldStocks(bookId, orderId, true);
    }

    private BookDisplayDto refreshHeldStocks(String bookId, String orderId, boolean isRestock) {
        Book book = findById(bookId);
        book.refreshHeldStocks(orderId, isRestock);
        book = save(book);
        return convert(book);
    }

    private Book findById (String bookId) {
        return bookService.findById(bookId);
    }

    private Book save (Book book) {
        return bookService.save(book);
    }

    private BookDisplayDto convert(Book book) {
        return BookDisplayDto.builder()
                .creationDate(book.getCreationDate())
                .bookName(book.getBookName())
                .price(book.getPrice())
                .stockSize(book.getStockSize())
                .authorName(book.getAuthorName())
                .bookId(book.getId())
                .build();
    }

    private Book convert(BookCreateDto bookCreateDto) {
        return Book.getInstance()
                .bookName(bookCreateDto.getBookName())
                .authorName(bookCreateDto.getAuthorName())
                .price(bookCreateDto.getPrice())
                .stockSize(bookCreateDto.getStockSize());
    }

    private void validateBook(Book book) {
        checkStocks(book.getStockSize());
        checkPrice(book.getPrice());
    }

    private void checkStocks(Integer stocks) {
        if (stocks.intValue() < 0)
            throw new BadRequestException("Stock cannot be less than zero!");
    }

    private void checkPrice(Double price) {
        if (price.doubleValue() < Double.valueOf(0).doubleValue())
            throw new BadRequestException("Price cannot be less than zero!");
    }
}
