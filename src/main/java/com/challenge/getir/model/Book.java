package com.challenge.getir.model;

import com.challenge.getir.exception.InsufficentStockException;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Document
public class Book {

    @Id
    private String id;

    private LocalDate creationDate = LocalDate.now();

    private String bookName;

    private String authorName;

    private Double price;

    private Integer stockSize;

    private Map<String, Integer> heldStocks = new HashMap<>();

    private Book() {
    }

    public static Book getInstance () {
        return new Book();
    }

    public Book bookName (String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Book authorName (String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Book price (Double price) {
        this.price = price;
        return this;
    }

    public Book stockSize (Integer stockSize) {
        this.stockSize = stockSize;
        return this;
    }

    public Book heldStocks (String orderId, Integer heldStock) {
        reduceStocks(heldStock);
        heldStocks.put(orderId, heldStock);
        return this;
    }

    public Book refreshHeldStocks(String orderId, boolean isRestock) {
        if (isRestock)
            releaseStocks(orderId);
        else
            heldStocks.remove(orderId);
        return this;
    }

    public Book refreshHeldStocks(Set<String> orderIds, boolean isRestock) {
        for (String orderId : orderIds)
            refreshHeldStocks(orderId, isRestock);
        return this;
    }

    private Book releaseStocks (String orderId) {
        Integer heldStockSize = heldStocks.get(orderId);
        increaseStocks(heldStockSize);
        heldStocks.remove(orderId);
        return this;
    }

    private Book reduceStocks (Integer boughtSize) {
        if (stockSize < boughtSize)
            throw new InsufficentStockException("Insufficent Stocks!");
        stockSize -= boughtSize;
        return this;
    }

    private Book increaseStocks (Integer size) {
        if (size != null)
            stockSize += size;
        return this;
    }
}
