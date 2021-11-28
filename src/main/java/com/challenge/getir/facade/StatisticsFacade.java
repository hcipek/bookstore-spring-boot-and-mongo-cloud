package com.challenge.getir.facade;

import com.challenge.getir.exception.EntityNotFoundException;
import com.challenge.getir.model.dto.CustomerStatisticsDto;
import com.challenge.getir.model.dto.OrderDisplayDto;
import com.challenge.getir.model.dto.StatisticDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class StatisticsFacade {

    private final OrderFacade orderFacade;

    public CustomerStatisticsDto getStatisticsByCustomerId(String customerId) {
        log.info("Getting statistics of Customer started with id : {}", customerId);
        List<OrderDisplayDto> orderDisplayDtos = orderFacade.getOrders(customerId);

        if (orderDisplayDtos.isEmpty())
            throw new EntityNotFoundException("This customer has not have any completed Orders");
        Map<String, StatisticDto> map = new HashMap<>();

        for (OrderDisplayDto orderDisplayDto : orderDisplayDtos) {
            String month = orderDisplayDto.getCreationDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            if (!map.containsKey(month))
                map.put(month, StatisticDto.builder().month(month).build());

            StatisticDto statisticDto = map.get(month);
            statisticDto.incrementOrder();
            statisticDto.addPurchaseAmount(orderDisplayDto.getTotalPrice());
            orderDisplayDto.getOrderDetails().forEach(e -> statisticDto.addBook(e.getCount()));

            map.put(month, statisticDto);
        }

        log.info("Getting statistics of Customer finished");
        return CustomerStatisticsDto.builder()
                .statistics(map.values())
                .build();
    }
}
