package com.services.invoice.repository;

import com.services.invoice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
