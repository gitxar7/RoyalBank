package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findByEmail", query = "select a from Account a where a.user.email = :email"),
        @NamedQuery(name = "Account.findByAccountNumber", query = "select a from Account a where a.accountNumber = :accountNumber")
})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String accountNumber;
    private double balance = 2000.00;
    private LocalDateTime creationDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;

    public Account(String accountNumber, Status status, User user) {
        this.accountNumber = accountNumber;
        this.status = status;
        this.user = user;
    }

    public Account() {}

    public long getId() {
        return id;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
