package com.challenge.getir.controller;

import com.challenge.getir.facade.OrderFacade;
import com.challenge.getir.model.ErrorResponse;
import com.challenge.getir.model.dto.OrderCreateDto;
import com.challenge.getir.model.dto.OrderDisplayDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @Operation(summary = "Create an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created an Order",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDisplayDto.class))),
            @ApiResponse(responseCode = "200", description = "Book doesnt exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "406", description = "Unavailable stocks",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDisplayDto> createOrder (@RequestBody @Valid OrderCreateDto orderCreateDto) {
        OrderDisplayDto createdOrder = orderFacade.createOrder(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @Operation(summary = "Gets an order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order by Requested Id",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDisplayDto.class))),
            @ApiResponse(responseCode = "200", description = "Order Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDisplayDto> getOrderById (@PathVariable("order_id") String orderId) {
        OrderDisplayDto requestedOrder = orderFacade.getOrderById(orderId);
        return ResponseEntity.ok(requestedOrder);
    }

    @Operation(summary = "Gets orders between given dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders between given dates",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<OrderDisplayDto>> getOrders (@RequestParam(value = "start_date") String startDate,
                                                            @RequestParam(value = "end_date") String endDate) {
        Page<OrderDisplayDto> requestedOrders = orderFacade.getOrdersByDateInterval(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(requestedOrders);
    }
}
