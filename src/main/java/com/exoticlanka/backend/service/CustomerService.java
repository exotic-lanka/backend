package com.exoticlanka.backend.service;

import com.exoticlanka.backend.dto.request.RequestCustomerDto;
import com.exoticlanka.backend.dto.response.ResponseCustomerDto;
import com.exoticlanka.backend.dto.response.paginated.PaginatedCustomerResponseDto;

import java.util.List;


public interface CustomerService {
    public void createCustomer(RequestCustomerDto dto);
    public ResponseCustomerDto getCustomer(long id);
    public void deleteCustomer(long id);
    public List<ResponseCustomerDto> findCustomersByName(String name);
    public void updateCustomer(long id,RequestCustomerDto dto);
    public PaginatedCustomerResponseDto getAllCustomers(String searchText, int page, int size);
}
