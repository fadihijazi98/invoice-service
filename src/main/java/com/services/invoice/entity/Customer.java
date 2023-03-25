package com.services.invoice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends Base {

    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Invoice> invoices;

    public Customer() {

    }

    public Customer(String name, String email, String address) {

        this.name = name;
        this.email = email;
        this.address = address;
    }

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
