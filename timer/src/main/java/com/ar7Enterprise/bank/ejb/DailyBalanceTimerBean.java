package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.email.DailyBalanceMail;
import com.ar7Enterprise.bank.core.email.LoginAlertMail;
import com.ar7Enterprise.bank.core.exception.TransactionException;
import com.ar7Enterprise.bank.core.model.Account;
import com.ar7Enterprise.bank.core.model.History;
import com.ar7Enterprise.bank.core.provider.MailServiceProvider;
import com.ar7Enterprise.bank.core.service.AccountService;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Startup
public class DailyBalanceTimerBean {

    @EJB
    private AccountService accountService;

    @PersistenceContext
    private EntityManager em;

    @Schedule(hour = "7", minute = "0", second = "0", persistent = true)
    public void sendDailyBalances() {
        List<Account> accounts = accountService.getAllAccounts();
        LocalDate date = LocalDate.now();

        for (Account account : accounts) {
           try {
               double balance = account.getBalance();
               String email = account.getUser().getEmail();
               String accountNumber = account.getAccountNumber();

               DailyBalanceMail mail = new DailyBalanceMail(email, date.toString(), accountNumber, balance);
               MailServiceProvider.getInstance().sendMail(mail);

               History h = new History();
               h.setTitle("Daily Balance Update");
               h.setDescription("Your current balance is " + balance + " at " + date);
               h.setUser(account.getUser());
               h.setDateTime(LocalDateTime.now());
               em.persist(h);
           } catch (Exception e) {
               throw new TransactionException("Could not send DailyBalanceMail/ Log, id:"+ account.getId());
           }
        }
    }
}
