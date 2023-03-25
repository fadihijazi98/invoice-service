package com.services.invoice.repository;

import com.services.invoice.entity.HourlyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyEmployeeRepo extends JpaRepository<HourlyEmployee, Integer> {
}
