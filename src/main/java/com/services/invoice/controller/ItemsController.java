package com.services.invoice.controller;

import com.services.invoice.entity.Item;
import com.services.invoice.repository.ItemRepo;
import com.services.invoice.validator.ItemRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsController {
    ItemRepo itemRepo;

    public ItemsController(ItemRepo itemRepo) {

        this.itemRepo = itemRepo;
    }

    @PostMapping("/api/v1/items")
    public ResponseEntity<Item> store(@Valid @RequestBody ItemRequest item) {

        return new ResponseEntity<>(
                this.itemRepo.save(item.toItem()),
                HttpStatus.CREATED
        );
    }
}
