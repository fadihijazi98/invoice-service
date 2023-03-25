package com.services.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.invoice.entity.Invoice;
import com.services.invoice.repository.InvoiceRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/invoices")
public class InvoicesController {

    private InvoiceRepo invoiceRepo;

    public InvoicesController(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<?> exportInvoices(@RequestParam("mode") String mode) {

        List<Invoice> invoices = this.invoiceRepo.findAll();

        List<Map<String, Object>> invoicesToExport = invoices.stream().map(
                invoice -> new HashMap<String, Object>() {{
                    put("id", invoice.getId());
                    put("date", invoice.getCreatedAt());
                    put("total", invoice.getTotal());
                }}
        ).collect(Collectors.toList());

        if (mode.equals("csv")) {

            return this.exportDataInCsvMode(invoicesToExport);
        } else if (mode.equals("json")) {

            return new ResponseEntity<>(invoicesToExport, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(
                    Map.of(
                            "message", "mode should be either 'json' or 'csv'."
                    ), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<String> exportDataInCsvMode(List<Map<String, Object>> invoicesToExport) {

        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("id,total,date\n");
        for (Map<String, Object> invoiceMap : invoicesToExport) {

            csvBuilder.append(
                    String.format(
                            "%s,%s,%s\n",
                            invoiceMap.get("id"),
                            invoiceMap.get("total"),
                            invoiceMap.get("date")
                    ));
        }
        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=items.csv");

        return new ResponseEntity<>(csvBuilder.toString(), headers, HttpStatus.OK);
    }
}
