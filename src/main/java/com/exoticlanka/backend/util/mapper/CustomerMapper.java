package com.exoticlanka.backend.util.mapper;

import com.exoticlanka.backend.dto.request.RequestCustomerDto;
import com.exoticlanka.backend.dto.response.ResponseCustomerDto;
import com.exoticlanka.backend.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    ResponseCustomerDto toResponseCustomerDto(Customer customer);
    Customer toCustomer(RequestCustomerDto dto);

    List<ResponseCustomerDto> toResponseCustomerDtoList(List<Customer> list);
}
