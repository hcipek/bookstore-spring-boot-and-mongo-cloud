package com.challenge.getir.controller;

import com.challenge.getir.facade.StatisticsFacade;
import com.challenge.getir.model.dto.CustomerStatisticsDto;
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
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsFacade statisticsFacade;

    @Operation(summary = "Gets Statistics Of a Customer By Customers Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics of Customer by Customer Id",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerStatisticsDto.class)))
    })
    @GetMapping("/{customer_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerStatisticsDto> getStatisticsByCustomerId (@PathVariable("customer_id") String customerId) {
        CustomerStatisticsDto statisticsDto = statisticsFacade.getStatisticsByCustomerId(customerId);
        return ResponseEntity.ok(statisticsDto);
    }
}
