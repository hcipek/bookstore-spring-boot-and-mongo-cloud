package com.challenge.getir.controller;

import com.challenge.getir.facade.CustomerFacade;
import com.challenge.getir.model.dto.CustomerCreateDto;
import com.challenge.getir.model.dto.CustomerDisplayDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerFacade customerFacade;

    @Operation(summary = "Create a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a Customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDisplayDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDisplayDto> createCustomer (@RequestBody @Valid CustomerCreateDto customerCreateDto) {
        CustomerDisplayDto createdCustomer = customerFacade.createCustomer(customerCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @Operation(summary = "Gets orders of customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders of requested customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @GetMapping("/{customer_id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<OrderDisplayDto>> getOrdersOfCustomer (@PathVariable("customer_id") String customerId,
                                                                      @RequestParam(value = "page_number", required = false, defaultValue = "0") Integer pageNumber,
                                                                      @RequestParam(value = "page_size", required = false, defaultValue = "20") Integer pageSize) {
        Page<OrderDisplayDto> pageResult = customerFacade.getOrdersByCustomerId(customerId, pageNumber, pageSize);
        return ResponseEntity.ok(pageResult);
    }
}
