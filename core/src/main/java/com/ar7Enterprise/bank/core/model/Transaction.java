package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
@NamedQueries({
        @NamedQuery(name = "Transaction.findPeriodicTransactions"
                , query = "select t from Transaction t where " +
                "(t.from = :user OR t.to = :user) and t.createdAt BETWEEN :from AND :to"),
})
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDateTime createdAt;

    public Transaction(User from, User to, double amount, TransactionType type) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        createdAt = LocalDateTime.now();
    }

    public Transaction() {
        createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
