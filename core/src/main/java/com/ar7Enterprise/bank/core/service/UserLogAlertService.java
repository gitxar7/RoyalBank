package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.User;

public interface UserLogAlertService {
    public void handleSuccessfulLogin(User user);
}
