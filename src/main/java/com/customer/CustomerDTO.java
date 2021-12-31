package com.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


@Value
public class CustomerDTO {
    String customerId;
    String firstName;
    String lastName;
    String email;
    String addressId;
    String address;
    String postalCode;

    @JsonCreator
    public CustomerDTO(
            @JsonProperty("customerId") String customerId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("addressId") String addressId,
            @JsonProperty("address") String address,
            @JsonProperty("postalCode") String postalCode){

        this.customerId=customerId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.addressId=addressId;
        this.address=address;
        this.postalCode=postalCode;
    }
}
