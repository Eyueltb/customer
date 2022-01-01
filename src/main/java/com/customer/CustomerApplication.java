package com.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerService customerService){
        return args -> {
                try {
                CreateCustomer customer1 = new CreateCustomer("Eyuel","Belay", "eyuel.belay@se.com","Sponga 127-Stockholm, Sweden","12744");
                customerService.saveCustomer(customer1);
                CreateCustomer customer2 = new CreateCustomer("John","Smith", "john.smith@se.com","linnaeus 124-Vaxjo, Sweden","16789");
                customerService.saveCustomer(customer2);
                CreateCustomer customer3 = new CreateCustomer("Jafer","Redi", "jafer.radi@se.com","Lafto 130-Addis Ababa, Ethiopia" ,"25235");
                customerService.saveCustomer(customer3);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        };
    }
}
