package com.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

import static com.customer.UseExceptionType.CUSTOMER_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public Stream<Customer> getCustomers(){
        return customerRepository.findAll().stream();
    }
    public Optional<Customer> getCustomer(String customerId) throws UseException {
        final Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UseException(CUSTOMER_NOT_FOUND));
        return Optional.of(customer);
    }

}
