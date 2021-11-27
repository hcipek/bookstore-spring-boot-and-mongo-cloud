package com.challenge.getir.controller;

import com.challenge.getir.facade.BookFacade;
import com.challenge.getir.model.dto.BookStockRequestDto;
import com.challenge.getir.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookFacade bookFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BookDto> createBook (@RequestBody BookDto bookDto) {
        BookDto createdBook = bookFacade.createBook(bookDto);
        return ResponseEntity.accepted().body(createdBook);
    }

    @PutMapping("/stock-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BookDto> stockUpdate (@RequestBody BookStockRequestDto bookStockRequestDto) {
        BookDto stockUpdatedBook = bookFacade.stockUpdate(bookStockRequestDto);
        return ResponseEntity.accepted().body(stockUpdatedBook);
    }
}
