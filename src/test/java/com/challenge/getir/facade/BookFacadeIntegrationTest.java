package com.challenge.getir.facade;

import com.challenge.getir.model.dto.BookCreateDto;
import com.challenge.getir.model.dto.BookDisplayDto;
import com.challenge.getir.model.dto.BookStockRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookFacadeIntegrationTest {

    @Autowired
    BookFacade bookFacade;

    static BookDisplayDto bookDisplayDto = new BookDisplayDto();

    @Test
    @Order(1)
    void saveBook_OK() {
        BookCreateDto bookCreateDto = BookCreateDto.builder()
                .bookName("test")
                .authorName("test")
                .stockSize(10)
                .price(9.99)
                .build();
        bookDisplayDto = bookFacade.createBook(bookCreateDto);
        assertEquals(bookCreateDto.getPrice(), bookDisplayDto.getPrice());
        assertEquals(bookCreateDto.getBookName(), bookDisplayDto.getBookName());
        assertEquals(bookCreateDto.getAuthorName(), bookDisplayDto.getAuthorName());
        assertEquals(bookCreateDto.getStockSize(), bookDisplayDto.getStockSize());
    }

    @Test
    @Order(2)
    void findById_OK() {
        BookDisplayDto findBook = bookFacade.getById(bookDisplayDto.getBookId());
        assertEquals(bookDisplayDto.getBookId(), findBook.getBookId());
    }

    @Test
    @Order(3)
    void stockUpdate_OK() {
        BookStockRequestDto dto = BookStockRequestDto.builder()
                .newStockSize(20)
                .bookId(bookDisplayDto.getBookId())
                .build();
        BookDisplayDto displayDto = bookFacade.stockUpdate(dto);

        assertEquals(bookDisplayDto.getBookId(), displayDto.getBookId());
        assertEquals(dto.getNewStockSize(), displayDto.getStockSize());

        bookDisplayDto = displayDto;
    }

    @Test
    @Order(4)
    void heldStocks_OK() {
        BookDisplayDto displayDto = bookFacade.heldStocks(bookDisplayDto.getBookId(), "test-order-id", 3);

        assertEquals(bookDisplayDto.getBookId(), displayDto.getBookId());
        assertEquals(bookDisplayDto.getStockSize() - 3, displayDto.getStockSize());

        bookDisplayDto = displayDto;
    }

    @Test
    @Order(5)
    void releaseStocks_OK() {
        BookDisplayDto displayDto = bookFacade.releaseStocks(bookDisplayDto.getBookId(), "test-order-id");

        assertEquals(bookDisplayDto.getBookId(), displayDto.getBookId());

        BookDisplayDto displayDto2 = bookFacade.returnStocks(bookDisplayDto.getBookId(), "test-order-id");

        assertEquals(displayDto.getStockSize(), displayDto2.getStockSize());

        bookDisplayDto = displayDto;
    }
}
