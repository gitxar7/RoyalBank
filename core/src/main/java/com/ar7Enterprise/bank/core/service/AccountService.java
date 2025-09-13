package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.Account;
import com.ar7Enterprise.bank.core.model.User;
import jakarta.ejb.Remote;

import java.util.List;

public interface AccountService {
    String generateAccountNumber(User user);
    Account getAccountByID(long id);
    Account getAccountByAccountNumber(String accountNumber);
    Account getAccountByEmail(String email);
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    void createAccount(String email);
    boolean doesUserHaveAccount(User user);
    boolean isAccountExist(String accountNumber);
    List<Account> getAllAccounts();
}
