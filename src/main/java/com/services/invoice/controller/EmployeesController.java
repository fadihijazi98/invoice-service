package com.services.invoice.controller;

import com.services.invoice.entity.*;
import com.services.invoice.entity.custom.IReport;
import com.services.invoice.repository.*;
import com.services.invoice.validator.HourlyEmployeeRequest;
import com.services.invoice.validator.InvoiceRequest;
import com.services.invoice.validator.SalariedEmployeeRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeesController {
    UserRepo userRepo;
    HourlyEmployeeRepo hourlyEmployeeRepo;
    SalariedEmployeeRepo salariedEmployeeRepo;
    InvoiceRepo invoiceRepo;
    ItemRepo itemRepo;
    InvoiceItemRepo invoiceItemRepo;
    CustomerRepo customerRepo;

    public EmployeesController(UserRepo userRepo, HourlyEmployeeRepo hourlyEmployeeRepo, SalariedEmployeeRepo salariedEmployeeRepo,
                               InvoiceRepo invoiceRepo, ItemRepo itemRepo, InvoiceItemRepo invoiceItemRepo, CustomerRepo customerRepo) {

        this.userRepo = userRepo;
        this.hourlyEmployeeRepo = hourlyEmployeeRepo;
        this.salariedEmployeeRepo = salariedEmployeeRepo;
        this.invoiceRepo = invoiceRepo;
        this.itemRepo = itemRepo;
        this.invoiceItemRepo = invoiceItemRepo;
        this.customerRepo = customerRepo;
    }

    private Map<String, Object> getReportAsMap(IReport report) {

        return Map.of(
                "day", report.getCreatedAt(),
                "invoice_count", report.getTotalInvoices(),
                "total_items", report.getTotalItems()
        );
    }

    @GetMapping("/{userId}/report")
    public Map<String, List<Map<String, Object>>> getEmployeeReport(@PathVariable Integer userId,
                                                                    @RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {

        List<IReport> invoiceReports =
                this.invoiceRepo.getReportsByDateRange(from, to, userId);
        List<Map<String, Object>> reportMap =
                invoiceReports.stream().map(this::getReportAsMap).collect(Collectors.toList());
        return Map.of(
                "data", reportMap
        );
    }

    @PostMapping("/hourly")
    public ResponseEntity<HourlyEmployee> createHourlyEmployee(@Valid @RequestBody HourlyEmployeeRequest employee) {

        User user = employee.toUser();
        HourlyEmployee hourlyEmployee = employee.toHourlyEmployee(user);

        this.userRepo.save(user);
        HourlyEmployee createdHourlyEmployee =
                this.hourlyEmployeeRepo.save(hourlyEmployee);

        return new ResponseEntity<>(createdHourlyEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/salaried")
    public ResponseEntity<SalariedEmployee> createSalariedEmployee(@Valid @RequestBody SalariedEmployeeRequest employee) {

        User user = employee.toUser();
        SalariedEmployee salariedEmployee = employee.toSalariedEmployee(user);

        this.userRepo.save(user);
        SalariedEmployee createdSalariedEmployee =
                this.salariedEmployeeRepo.save(salariedEmployee);

        return new ResponseEntity<>(createdSalariedEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/invoices")
    public ResponseEntity<Invoice> createInvoice(@PathVariable Integer userId, @RequestBody InvoiceRequest invoice) {

        List<Integer> itemIds =
                invoice.items.stream().map(invoiceItem -> invoiceItem.item_id).collect(Collectors.toList());
        List<Item> items =
                this.itemRepo.findAllById(itemIds);

        if (items.size() != invoice.items.size()) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<Integer, Item> itemMap =
                items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));
        User user =
                this.userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        Customer customer =
                this.customerRepo.findById(invoice.customer_id).orElseThrow(NoSuchElementException::new);

        Invoice createdInvoice =
                new Invoice(customer, user);
        List<InvoiceItem> createdInvoiceItems =
                invoice.items.stream()
                        .map(item -> new InvoiceItem(item.quantity, createdInvoice, itemMap.get(item.item_id)))
                        .collect(Collectors.toList());

        this.invoiceRepo.save(createdInvoice);
        this.invoiceItemRepo.saveAll(createdInvoiceItems);

        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

}
