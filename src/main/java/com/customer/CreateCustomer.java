package com.customer;

import lombok.Value;

@Value
public class CreateCustomer {
    String firstName;
    String lastName;
    String email;
    String address;
    String postalCode;
}
