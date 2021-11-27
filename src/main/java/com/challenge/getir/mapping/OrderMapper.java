package com.challenge.getir.mapping;

import com.challenge.getir.model.Order;
import com.challenge.getir.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({

    })
    OrderDto map (Order order);

    @Mappings({

    })
    Order map (OrderDto orderDto);
}
