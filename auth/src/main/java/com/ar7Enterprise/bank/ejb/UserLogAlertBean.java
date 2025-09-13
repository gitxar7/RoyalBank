package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.UserLogAlert;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.UserLogAlertService;
import jakarta.enterprise.context.ApplicationScoped;

//@ApplicationScoped
public class UserLogAlertBean implements UserLogAlertService {

    @UserLogAlert
    @Override
    public void handleSuccessfulLogin(User user) {
    }
}
