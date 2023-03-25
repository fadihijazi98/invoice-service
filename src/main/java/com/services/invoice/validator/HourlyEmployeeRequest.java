package com.services.invoice.validator;

import com.services.invoice.entity.HourlyEmployee;
import com.services.invoice.entity.User;
import com.services.invoice.enums.UserTypes;
import com.services.invoice.enums.WorkShifts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HourlyEmployeeRequest extends BaseEmployeeRequest {

    @Valid
    @NotNull(message = "hours `shift` is required.")
    private WorkShifts shift;

    @NotNull(message = "hour `rate` is required")
    @Positive(message = "hour `rate` should be more than zero.")
    private float rate;

    public HourlyEmployeeRequest(String ssn, String first_name, String last_name,
                                 String email, String password, String phone,
                                 WorkShifts shift, float rate) {

        super(ssn, first_name, last_name, email, password, phone);
        this.shift = shift;
        this.rate = rate;
    }

    @Override
    protected UserTypes getUserType() {
        return UserTypes.HOURLY_EMPLOYEE;
    }

    public HourlyEmployee toHourlyEmployee(User relatedUser) {

        return new HourlyEmployee(
                this.shift,
                this.rate,
                relatedUser
        );
    }
}
