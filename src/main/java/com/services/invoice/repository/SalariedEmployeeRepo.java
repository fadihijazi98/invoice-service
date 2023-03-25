package com.services.invoice.repository;

import com.services.invoice.entity.SalariedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalariedEmployeeRepo extends JpaRepository<SalariedEmployee, Integer> {
}
