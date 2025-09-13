package com.ar7Enterprise.bank.core.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Cacheable(false)
@Entity
@NamedQueries({
        @NamedQuery(name = "ScheduledTransaction.dueTransactions",
                query = "select s from ScheduledTransaction s where s.scheduledDate <= :today and s.processed = false")
})
public class ScheduledTransaction implements Serializable {
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
    private LocalDate scheduledDate;
    private boolean processed = false;
    private LocalDateTime createdDate = LocalDateTime.now();

    public ScheduledTransaction(User from, User to, double amount, TransactionType type, LocalDate scheduledDate) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.scheduledDate = scheduledDate;
        this.createdDate = createdDate;
    }

    public ScheduledTransaction() {
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

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
