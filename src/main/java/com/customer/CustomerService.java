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

    public Optional<Customer> saveCustomer(CreateCustomer createCustomer){
        Address address = new Address(createCustomer.getAddress(), createCustomer.getPostalCode());
        addressRepository.save(address);
        Customer customer = new Customer(createCustomer.getFirstName(), createCustomer.getLastName(), createCustomer.getEmail(), address);

        return  Optional.of(customerRepository.save(customer));
    }

    public void removeCustomer(String customerId) throws UseException {
        final Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new UseException(CUSTOMER_NOT_FOUND));
        final Address address = customer.getAddress();
        customerRepository.delete(customer);
        addressRepository.delete(address);
   }


    public Optional<Customer> updateCustomer(String customerId, CreateCustomer createCustomer) throws UseException {

        final Customer oldCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UseException(CUSTOMER_NOT_FOUND));
        var oldAddress = oldCustomer.getAddress();
        updateCustomer(createCustomer, oldCustomer, oldAddress);
        return Optional.ofNullable(customerRepository.save(oldCustomer));
    }

    private void updateCustomer(CreateCustomer createCustomer, Customer oldCustomer, Address oldAddress) {
        oldAddress.setAddress(createCustomer.getAddress());
        oldAddress.setPostCode(createCustomer.getPostalCode());
        addressRepository.save(oldAddress);
        oldCustomer.setFirstName(createCustomer.getFirstName());
        oldCustomer.setLastName(createCustomer.getLastName());
        oldCustomer.setEmail(createCustomer.getEmail());
        oldCustomer.setAddress(oldAddress);
    }
}
