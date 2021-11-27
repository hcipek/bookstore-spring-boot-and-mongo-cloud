package com.challenge.getir.mapping;

import com.challenge.getir.model.Book;
import com.challenge.getir.model.Customer;
import com.challenge.getir.model.dto.BookDto;
import com.challenge.getir.model.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mappings({

    })
    BookDto map (Book book);

    @Mappings({

    })
    Book map (BookDto bookDto);
}
