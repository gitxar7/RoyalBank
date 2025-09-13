package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Cacheable(false)
@Entity
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double interest;
    private double minimum;

    public long getId() {
        return id;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }
}
