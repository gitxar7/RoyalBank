package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.model.Account;
import com.ar7Enterprise.bank.core.model.Bank;
import com.ar7Enterprise.bank.core.model.History;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.InterestService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Logged
public class InterestBean implements InterestService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private AccountService accountService;

    @Override
    public void applyInterestToAllAccounts() {
        Bank bank = em.createQuery("SELECT b FROM Bank b", Bank.class).getSingleResult();
        double rate = bank.getInterest();
        List<Account> accounts = accountService.getAllAccounts();
        for (Account account : accounts) {
            double interest = account.getBalance() * (rate / 100.0);
            account.setBalance(account.getBalance() + interest);
            em.merge(account);

            History h = new History();
            h.setUser(account.getUser());
            h.setTitle("Interest Applied");
            h.setDescription("An interest of " + interest + " has been applied to your account.");
            h.setDateTime(LocalDateTime.now());
            em.persist(h);
        }
    }
}
