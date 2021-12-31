package com.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    private String address;
    private String postCode;

    public Address (String address,String postCode ){
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.postCode =postCode;
    }
}
