package com.services.invoice.repository;

import com.services.invoice.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepo extends JpaRepository<InvoiceItem, Integer> {
}
