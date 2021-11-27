package com.challenge.getir.mapping;

import com.challenge.getir.model.Customer;
import com.challenge.getir.model.Order;
import com.challenge.getir.model.dto.CustomerDto;
import com.challenge.getir.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({

    })
    CustomerDto map (Customer customer);

    @Mappings({

    })
    Customer map (CustomerDto customerDto);
}
