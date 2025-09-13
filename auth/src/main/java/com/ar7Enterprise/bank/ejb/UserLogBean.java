package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.model.LogEntry;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.LogService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserLogBean implements LogService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private UserService userService;

    @Override
    public void saveLog(LogEntry log) {
        em.persist(log);
    }
}
