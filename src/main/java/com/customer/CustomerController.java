package com.customer;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers().map(this::toCustomerDTO).collect(Collectors.toList());
    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        Address address = customer.getAddress();

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                address.getId(),
                address.getAddress(),
                address.getPostCode()
        );
    }


}
