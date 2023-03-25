package com.services.invoice.validator;

import com.services.invoice.entity.SalariedEmployee;
import com.services.invoice.entity.User;
import com.services.invoice.enums.ContractUnits;
import com.services.invoice.enums.UserTypes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SalariedEmployeeRequest extends BaseEmployeeRequest {

    @Positive(message = "`contract_period` is required & should be more than zero.")
    private int contract_period;

    @Valid
    @NotNull(message = "`contract_unit` is required.")
    private ContractUnits contract_unit;

    @Positive(message = "`salary` is required & should be more than zero.")
    private float salary;

    @Override
    protected UserTypes getUserType() {
        return UserTypes.SALARIED_EMPLOYEE;
    }

    public SalariedEmployeeRequest(String ssn, String first_name, String last_name, String email,
                                   String password, String phone, ContractUnits contract_unit,
                                   int contract_period, float salary) {

        super(ssn, first_name, last_name, email, password, phone);
        this.contract_period = contract_period;
        this.contract_unit = contract_unit;
        this.salary = salary;
    }

    public SalariedEmployee toSalariedEmployee(User relatedUser) {

        return new SalariedEmployee(
                this.salary,
                this.contract_period,
                this.contract_unit,
                relatedUser
        );
    }
}
