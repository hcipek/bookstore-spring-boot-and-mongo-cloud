package com.challenge.getir.facade;

import com.challenge.getir.exception.InsufficentStockException;
import com.challenge.getir.model.dto.BookDisplayDto;
import com.challenge.getir.model.dto.OrderDetailDisplayDto;
import com.challenge.getir.model.dto.OrderDisplayDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class BookOrderStockFacade {

    private final BookFacade bookFacade;

    public OrderDisplayDto heldStocksByOrder (OrderDisplayDto orderDisplayDto) {
        log.info("Helding stocks operation for order started : {}", orderDisplayDto.toString());
        List<String> bookIds = orderDisplayDto.getOrderDetails().stream()
                .map(OrderDetailDisplayDto::getBookId)
                .collect(Collectors.toList());
        try {
            for (OrderDetailDisplayDto dto : orderDisplayDto.getOrderDetails()) {
                log.info("Trying to add requested book. Id : {}, Count : {}", dto.getBookId(), dto.getCount());
                BookDisplayDto bookDisplayDto = bookFacade.heldStocks(dto.getBookId(), orderDisplayDto.getOrderId(), dto.getCount());
                dto.setPricePerUnit(bookDisplayDto.getPrice());
            }
        } catch (InsufficentStockException e) {
            log.error("Book has not enough stock! All stocks refreshed");
            bookFacade.returnStocks(bookIds, orderDisplayDto.getOrderId());
            throw e;
        }
        bookFacade.releaseStocks(bookIds, orderDisplayDto.getOrderId());
        log.info("Helding stocks operation for order finished : {}", orderDisplayDto.toString());
        return orderDisplayDto;
    }
}
