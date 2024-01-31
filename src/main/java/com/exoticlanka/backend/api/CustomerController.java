package com.exoticlanka.backend.api;

import com.exoticlanka.backend.dto.request.RequestCustomerDto;
import com.exoticlanka.backend.service.CustomerService;
import com.exoticlanka.backend.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto){
        customerService.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer was saved!",customerDto.getName()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable Long id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer data!",customerService.getCustomer(id)),
                HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    public ResponseEntity<StandardResponse> updateCustomer(
            @RequestParam long id,
            @RequestBody RequestCustomerDto customerDto
    ){
        customerService.updateCustomer(id,customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Update data!",customerDto.getName()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Deleted data!",id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(value = "/list", params = {"searchText","page","size"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<StandardResponse> findAllCustomer(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"data List!",customerService.getAllCustomers(
                        searchText, page, size)),
                HttpStatus.OK
        );
    }
}
