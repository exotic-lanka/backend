package com.exoticlanka.backend.service.impl;

import com.exoticlanka.backend.dto.request.RequestCustomerDto;
import com.exoticlanka.backend.dto.response.ResponseCustomerDto;
import com.exoticlanka.backend.dto.response.paginated.PaginatedCustomerResponseDto;
import com.exoticlanka.backend.entity.Customer;
import com.exoticlanka.backend.exceptions.EntryNotFoundException;
import com.exoticlanka.backend.repo.CustomerRepo;
import com.exoticlanka.backend.service.CustomerService;
import com.exoticlanka.backend.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(RequestCustomerDto dto) {

        UUID uuid=UUID.randomUUID();
        long customerId=Math.abs(uuid.getMostSignificantBits());

        Customer customer=new Customer(
                customerId, dto.getName(), dto.getAddress(),dto.getContact()
        );

        customerRepo.save(customer);

    }

    @Override
    public ResponseCustomerDto getCustomer(long id) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found!");
        }
        return customerMapper.toResponseCustomerDto(selectedCustomer.get());

    }

    @Override
    public void deleteCustomer(long id) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        customerRepo.deleteById(selectedCustomer.get().getId());
    }

    @Override
    public List<ResponseCustomerDto> findCustomersByName(String name) {
         customerRepo.findAllByName(name);
         return null;
    }

    @Override
    public void updateCustomer(long id, RequestCustomerDto dto) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if(selectedCustomer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        Customer customer=selectedCustomer.get();
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setContact(dto.getContact());
        customerRepo.save(customer);
    }

    @Override
    public PaginatedCustomerResponseDto getAllCustomers(String searchText, int page, int size) {
        searchText="%"+searchText+"%";
        List<Customer> customers = customerRepo.searchCustomers(searchText, PageRequest.of(page, size));
        long customerCount = customerRepo.countCustomers(searchText);
        List<ResponseCustomerDto> dtos= customerMapper.toResponseCustomerDtoList(customers);
     /*   customers.forEach(customer -> {
            dtos.add(
                    new ResponseCustomerDto(
                            customer.getId(),customer.getName(),
                            customer.getAddress(),customer.getContact()
                    )
            );
        });*/

        return new PaginatedCustomerResponseDto(
                customerCount,
                dtos
        );
    }
}
