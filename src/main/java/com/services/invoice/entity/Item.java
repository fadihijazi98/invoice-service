package com.services.invoice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item extends Base {
    private String name;
    private String barcode;
    private float price;

    public Item() {}

    public Item(Integer id) {
        this.id = id;
    }

    public Item(String name, String barcode, float price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
