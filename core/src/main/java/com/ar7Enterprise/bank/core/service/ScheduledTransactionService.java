package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.ScheduledTransaction;
import jakarta.ejb.Remote;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledTransactionService {
    List<ScheduledTransaction> getDueTransactions(LocalDate today);

    void addScheduledTransaction(ScheduledTransaction transaction);

    void markProcessed(ScheduledTransaction transaction);
}
