package com.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity(name="customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;
    private String email;

    @OneToOne
    private Address address;

    public Customer(String firstname, String lastName, String email, Address address){
        this.id = UUID.randomUUID().toString();
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }
}
