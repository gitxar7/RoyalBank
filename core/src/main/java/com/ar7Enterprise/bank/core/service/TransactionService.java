package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.Transaction;
import com.ar7Enterprise.bank.core.model.User;
import jakarta.ejb.Remote;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    void creditToAccount(String accountNumber, double amount);
    void debitFromAccount(String accountNumber, double amount);
    void transferAmount(String sourceAccountNumber, String destinationAccountNumber, double amount);
    public List<Transaction> getUserTransactionsInPeriod(User user, LocalDateTime from, LocalDateTime to);
}
