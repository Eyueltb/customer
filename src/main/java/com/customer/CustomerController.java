package com.customer;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") String customerId) throws UseException {
        return customerService.getCustomer(customerId)
                .map(this::toCustomerDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.CUSTOMER_ALREADY_EXIST));
    }
    @PostMapping("")
    public ResponseEntity<?> saveCustomer(@RequestBody CreateCustomer createCustomer) throws UseException {
        return ResponseEntity.ok(customerService.saveCustomer(createCustomer).map(this::toCustomerDTO));
    }

    @PatchMapping("update/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable("customerId") String customerId,
                                           @RequestBody CreateCustomer createCustomer) throws UseException {
        return customerService.updateCustomer(customerId, createCustomer)
                .map(this::toCustomerDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.CUSTOMER_NOT_FOUND));
    }
    @DeleteMapping("/removeCustomer/{customerId}")
    public void removeCustomer(@PathVariable("customerId") String customerId) throws Exception {
        customerService.removeCustomer(customerId);
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
