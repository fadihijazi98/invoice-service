package com.services.invoice.entity;

import com.services.invoice.enums.ContractUnits;
import jakarta.persistence.*;

@Entity
public class SalariedEmployee extends Base {

    private float salary;

    private int contractPeriod;

    @Enumerated(EnumType.STRING)
    private ContractUnits contractUnit;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public SalariedEmployee() {}

    public SalariedEmployee(float salary, int contractPeriod, ContractUnits contractUnit, User user) {
        this.salary = salary;
        this.contractPeriod = contractPeriod;
        this.contractUnit = contractUnit;
        this.user = user;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(int contract_period) {
        this.contractPeriod = contract_period;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContractUnits getContractUnit() {
        return contractUnit;
    }

    public void setContractUnit(ContractUnits contractUnit) {
        this.contractUnit = contractUnit;
    }
}
