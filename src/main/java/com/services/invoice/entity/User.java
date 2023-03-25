package com.services.invoice.entity;

import com.services.invoice.enums.UserTypes;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User extends Base {

    private String ssn;
    private String firstName;
    private String lastName;
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserTypes type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Invoice> invoices;

    public User() {
    }

    public User(String ssn, String firstName, String lastName, String phone, String email, String password, UserTypes type) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
