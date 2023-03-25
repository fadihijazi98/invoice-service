package com.services.invoice.validator;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRequest {

    public Integer customer_id;
    public List<InvoiceItemsRequest> items;
}
