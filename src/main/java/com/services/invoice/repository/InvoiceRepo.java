package com.services.invoice.repository;

import com.services.invoice.entity.Invoice;
import com.services.invoice.entity.custom.IReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

    @Query(value = """
            SELECT
                cast(invoice.createdAt as date) as createdAt,
                COUNT(DISTINCT invoice.id) as totalInvoices,
                SUM(invoiceItem.quantity) as totalItems
            FROM
                Invoice invoice
            LEFT JOIN
                InvoiceItem invoiceItem ON invoiceItem.invoice.id = invoice.id
            WHERE
                cast(invoice.createdAt as date) >= :startDate AND cast(invoice.updatedAt as date) <= :endDate AND invoice.user.id = :userId
            GROUP BY
                cast(invoice.createdAt as date)
            """)
    List<IReport> getReportsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                        @Param("userId") Integer userId);
}
