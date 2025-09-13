package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.exception.TransactionException;
import com.ar7Enterprise.bank.core.model.ScheduledTransaction;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.ScheduledTransactionService;
import com.ar7Enterprise.bank.core.service.TransactionService;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ScheduledTransactionTimerBean {

    @EJB
    private AccountService accountService;

    @EJB
    private TransactionService transactionService;

    @EJB
    private ScheduledTransactionService scheduledTransactionService;

    @Schedule(hour = "1", minute = "0", second = "0", persistent = true)
    public void processScheduledTransfers() {
        List<ScheduledTransaction> transactions = scheduledTransactionService.getDueTransactions(LocalDate.now());
        for (ScheduledTransaction transaction : transactions) {
            try {

                String fromAccountNumber = accountService.getAccountByEmail(transaction.getFrom().getEmail()).getAccountNumber();
                String toAccountNumber = accountService.getAccountByEmail(transaction.getTo().getEmail()).getAccountNumber();
                double amount = transaction.getAmount();

                transactionService.transferAmount(
                        fromAccountNumber,
                        toAccountNumber,
                        amount
                );
                scheduledTransactionService.markProcessed(transaction);
            } catch (Exception e) {
                throw new TransactionException("Transaction could not be processed, id:"+ transaction.getId());
            }
        }
    }
}
