package com.services.invoice.validator;

import com.services.invoice.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerRequest {

    @NotNull(message = "`name` is required.")
    private String name;

    @NotNull(message = "`email` is required")
    @Email(message = "`email` isn't valid.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z]+,[a-zA-Z]+,[a-zA-Z]+$", message = "address not valid.")
    private String address;

    public CustomerRequest(String name, String email, String address) {

        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer toCustomer() {

        return new Customer(
                this.name,
                this.email,
                this.address
        );
    }
}
