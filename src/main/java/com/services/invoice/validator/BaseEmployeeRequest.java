package com.services.invoice.validator;

import com.services.invoice.entity.User;
import com.services.invoice.enums.UserTypes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class BaseEmployeeRequest {
    @NotNull(message = "ssn is required.")
    public String ssn;

    @NotNull(message = "first_name is required.")
    private String first_name;

    @NotNull(message = "last_name is required.")
    private String last_name;

    @NotNull(message = "email is required.")
    @Email(message = "email isn't valid.")
    private String email;

    @NotNull(message = "password is required.")
    private String password;

    @NotNull(message = "phone is required.")
    private String phone;

    public BaseEmployeeRequest(String ssn, String first_name, String last_name, String email, String password, String phone) {

        this.ssn = ssn;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    abstract protected UserTypes getUserType();
    public User toUser()
    {
        return new User(
                ssn,
                first_name,
                last_name,
                phone,
                email,
                password,
                this.getUserType()
        );
    }

}
