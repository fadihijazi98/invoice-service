package com.services.invoice.validator;

import com.services.invoice.entity.Item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemRequest {

    @NotNull(message = "`name` is required.")
    private String name;

    @NotNull(message = "`barcode` is required.")
    @Size(min = 9, max = 9, message = "barcode should be 9 characters.")
    private String barcode;

    @Positive(message = "price is required & should be more than zero.")
    private float price;

    public ItemRequest(String name, String barcode, float price) {

        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public Item toItem() {

        return new Item(
                this.name,
                this.barcode,
                this.price
        );
    }
}
