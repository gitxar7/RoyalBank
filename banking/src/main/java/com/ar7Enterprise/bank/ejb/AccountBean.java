package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.model.Account;
import com.ar7Enterprise.bank.core.model.History;
import com.ar7Enterprise.bank.core.model.Status;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Logged
public class AccountBean implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private UserService userService;

    @Override
    public String generateAccountNumber(User user) {
        String bankCode = "RB";
        String year = String.valueOf(LocalDate.now().getYear());
        String id = String.format("%03d", user.getId());
        return bankCode + year + id;
    }

    @Override
    public Account getAccountByID(long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        try {
            return em.createNamedQuery("Account.findByAccountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Account getAccountByEmail(String email) {
        try {
            return em.createNamedQuery("Account.findByEmail", Account.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addAccount(Account account) {
        em.persist(account);
    }

    @Override
    @Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    @Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
    public void deleteAccount(Account account) {
        em.remove(account);
    }

    @Override
    public void createAccount(String email) {
        User user = userService.getUserByEmail(email);
        if (!doesUserHaveAccount(user)) {
            String accountNumber = generateAccountNumber(user);
            Account account = new Account(accountNumber, Status.ACTIVE, user);
            addAccount(account);
            History h = new History();
            h.setUser(account.getUser());
            h.setTitle("Account Created");
            h.setDescription("Your Bank Account has been created. Account Number: " + accountNumber);
            h.setDateTime(LocalDateTime.now());
            em.persist(h);
        }
    }

    @Override
    public boolean doesUserHaveAccount(User user) {
        Account account = getAccountByEmail(user.getEmail());
        return account != null;
    }

    @Override
    public boolean isAccountExist(String accountNumber) {
        Account account = getAccountByAccountNumber(accountNumber);
        return account != null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }
}
