package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.model.*;
import com.ar7Enterprise.bank.core.service.ScheduledTransactionService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Logged
@Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
public class ScheduledTransactionBean implements ScheduledTransactionService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private UserService userService;

    @Override
    public List<ScheduledTransaction> getDueTransactions(LocalDate today) {
        return em.createNamedQuery("ScheduledTransaction.dueTransactions", ScheduledTransaction.class)
                .setParameter("today", today)
                .getResultList();
    }

    @Override
    public void addScheduledTransaction(ScheduledTransaction transaction) {
        if (isDateValid(transaction.getScheduledDate())) {
            em.persist(transaction);
        } else {
            //Transaction Exception
        }
    }

    @Override
    public void markProcessed(ScheduledTransaction scheduledTransaction) {
        scheduledTransaction.setProcessed(true);
        em.merge(scheduledTransaction);
        History h = new History();
        h.setUser(scheduledTransaction.getFrom());
        h.setTitle("Transaction Successful.");
        h.setDescription("Scheduled Transaction to " + scheduledTransaction.getTo()
                + " at " + scheduledTransaction.getScheduledDate() + " has been completed.");
        h.setDateTime(LocalDateTime.now());
        em.persist(h);

        Transaction transaction = new Transaction(scheduledTransaction.getFrom(),
                scheduledTransaction.getTo(),
                scheduledTransaction.getAmount(),
                TransactionType.TRANSFER);
        em.persist(transaction);
    }

    public boolean isDateValid(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }
}
