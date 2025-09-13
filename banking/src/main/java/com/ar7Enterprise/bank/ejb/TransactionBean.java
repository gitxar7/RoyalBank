package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.exception.TransactionException;
import com.ar7Enterprise.bank.core.model.*;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.TransactionService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Logged
@Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
public class TransactionBean implements TransactionService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private AccountService accountService;

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void creditToAccount(String accountNumber, double amount) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            accountService.updateAccount(account);

            History h = new History();
            h.setUser(account.getUser());
            h.setTitle("Credited");
            h.setDescription("Your account has been credited by Rs. " + amount);
            h.setDateTime(LocalDateTime.now());
            em.persist(h);
        } else {
            throw new TransactionException("Amount is lesser than zero");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void debitFromAccount(String accountNumber, double amount) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        if (amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountService.updateAccount(account);

            History h = new History();
            h.setUser(account.getUser());
            h.setTitle("Debited");
            h.setDescription("Your account has been debited by Rs. " + amount);
            h.setDateTime(LocalDateTime.now());
            em.persist(h);
        } else {
            throw new TransactionException("Amount is lesser than zero or insufficient balance");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void transferAmount(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        if (accountService.isAccountExist(destinationAccountNumber)) {
            debitFromAccount(sourceAccountNumber, amount);
            creditToAccount(destinationAccountNumber, amount);

            User fromUser = accountService.getAccountByAccountNumber(sourceAccountNumber).getUser();
            User toUser = accountService.getAccountByAccountNumber(destinationAccountNumber).getUser();
            History h = new History();
            h.setUser(fromUser);
            h.setTitle("Transaction Successful");
            h.setDescription("Your transaction to " + destinationAccountNumber + " has been transferred by Rs. " + amount);
            h.setDateTime(LocalDateTime.now());
            em.persist(h);

            Transaction transaction = new Transaction(fromUser, toUser, amount, TransactionType.TRANSFER);
            em.persist(transaction);
        }
    }

    @Override
    public List<Transaction> getUserTransactionsInPeriod(User user, LocalDateTime from, LocalDateTime to) {
        return em.createNamedQuery("Transaction.findPeriodicTransactions", Transaction.class)
                .setParameter("user", user)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
}
