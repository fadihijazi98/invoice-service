package com.services.invoice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.services.invoice.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Invoice extends Base {
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("invoices")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "added_by")
    @JsonIgnoreProperties("invoices")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
    @JsonIgnoreProperties("invoice")
    private List<InvoiceItem> invoiceItems;

    public Invoice() {

        this.status = Status.ORDERED;
    }

    public Invoice(Customer customer, User user) {

        this();
        this.customer = customer;
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public float getTotal() {

        float total = 0f;
        for (InvoiceItem invoiceItem : this.invoiceItems) {

            total += invoiceItem.getQuantity() * invoiceItem.getItem().getPrice();
        }
        return total;
    }
}
