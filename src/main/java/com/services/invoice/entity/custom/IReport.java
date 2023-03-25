package com.services.invoice.entity.custom;

import java.time.LocalDate;

public interface IReport {

    public LocalDate getCreatedAt();
    public Long getTotalInvoices();
    public Long getTotalItems();
}
