package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.User;
import jakarta.ejb.Remote;

public interface UserService {
    User getUserById(long id);

    User getUserByEmail(String email);

    User getUserByEmailLog(String email);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    boolean isUserValid(String email, String password);

    void verifyUser(String email, String code);

    void activateUser(String email);

    void deactivateUser(String email);

    boolean isUserActive(String email);

    void addAnAdmin(String email);
}
