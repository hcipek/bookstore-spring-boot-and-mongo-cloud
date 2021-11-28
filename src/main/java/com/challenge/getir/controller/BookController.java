package com.challenge.getir.controller;

import com.challenge.getir.facade.BookFacade;
import com.challenge.getir.model.dto.BookCreateDto;
import com.challenge.getir.model.dto.BookStockRequestDto;
import com.challenge.getir.model.dto.BookDisplayDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookFacade bookFacade;

    @Operation(summary = "Create a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a Book",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDisplayDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDisplayDto> createBook (@RequestBody BookCreateDto bookCreateDto) {
        BookDisplayDto createdBook = bookFacade.createBook(bookCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @Operation(summary = "Updates stock of a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Stock updated of a Book",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDisplayDto.class))),
            @ApiResponse(responseCode = "200", description = "Book not found",
                    content = @Content)
    })
    @PutMapping("/stock-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<BookDisplayDto> stockUpdate (@RequestBody BookStockRequestDto bookStockRequestDto) {
        BookDisplayDto stockUpdatedBook = bookFacade.stockUpdate(bookStockRequestDto);
        return ResponseEntity.accepted().body(stockUpdatedBook);
    }
}
