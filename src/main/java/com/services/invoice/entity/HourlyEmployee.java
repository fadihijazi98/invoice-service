package com.services.invoice.entity;


import com.services.invoice.enums.WorkShifts;
import jakarta.persistence.*;

@Entity
public class HourlyEmployee extends Base {

    @Enumerated(EnumType.STRING)
    private WorkShifts shift;

    @Column(nullable = false)
    private float rate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public HourlyEmployee() {

    }

    public HourlyEmployee(WorkShifts shift, float rate, User user) {
        this.shift = shift;
        this.rate = rate;
        this.user = user;
    }

    public WorkShifts getShift() {
        return shift;
    }

    public void setShift(WorkShifts shift) {
        this.shift = shift;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
