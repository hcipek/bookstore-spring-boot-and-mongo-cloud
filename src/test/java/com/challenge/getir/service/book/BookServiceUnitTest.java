package com.challenge.getir.service.book;

import com.challenge.getir.model.Book;
import com.challenge.getir.model.dto.BookStockRequestDto;
import com.challenge.getir.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    BookServiceImpl bookService = null;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void saveBook_OK() {
        Book book = Book.getInstance()
                .id("test-id")
                .bookName("test")
                .authorName("test")
                .stockSize(10);
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.save(book);

        assertEquals(savedBook, book);
        assertEquals(savedBook.getBookName(), book.getBookName());
        assertEquals(savedBook.getAuthorName(), book.getAuthorName());
        assertEquals(savedBook.getStockSize(), book.getStockSize());
    }

    @Test
    void findById_OK() {
        Book book = Book.getInstance()
                .id("test-id")
                .bookName("test")
                .authorName("test")
                .stockSize(10);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Book findedBook = bookService.findById(book.getId());

        assertEquals(findedBook, book);
    }

    @Test
    void stockUpdate_OK() {
        Book book = Book.getInstance()
                .id("test-id")
                .bookName("test")
                .authorName("test")
                .stockSize(10);

        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.save(book);

        assertEquals(savedBook, book);

        BookStockRequestDto dto = new BookStockRequestDto(savedBook.getId(), 20);
        Book expectedBook = savedBook.stockSize(dto.getNewStockSize());
        when(bookRepository.findById(dto.getBookId())).thenReturn(Optional.of(savedBook));
        when(bookRepository.save(savedBook)).thenReturn(savedBook);
        savedBook = bookService.stockUpdate(dto);

        assertEquals(savedBook, expectedBook);
    }
}
