package com.ar7Enterprise.bank.core.interceptor;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.model.LogEntry;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.LogService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Interceptor
@Logged
@Priority(2)
public class LoggingInterceptor {

    @Inject
    private SecurityContext securityContext;

    private LogService logService;
    private UserService userService;

    private void initServices() throws NamingException {
        if (logService == null || userService == null) {
            InitialContext ctx = new InitialContext();
            logService = (LogService) ctx.lookup("java:global/royal-bank-ear/auth-ejb-module/UserLogBean!com.ar7Enterprise.bank.core.service.LogService");
            userService = (UserService) ctx.lookup("java:global/royal-bank-ear/auth-ejb-module/UserBean!com.ar7Enterprise.bank.core.service.UserService");
        }
    }

    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        initServices();

        if (ctx.getMethod().getDeclaringClass().getName().contains("AuthMechanism") ||
                ctx.getMethod().getDeclaringClass().getName().contains("AppIdentityStore")) {
            return ctx.proceed();
        }
        LogEntry log = new LogEntry();

        log.setClassName(ctx.getTarget().getClass().getSimpleName());
        log.setMethod(ctx.getMethod().getName());
        log.setTimestamp(LocalDateTime.now());
        log.setParameters(Arrays.toString(ctx.getParameters()));
        log.setActionType("METHOD_CALL");

        if (securityContext.getCallerPrincipal() != null) {
            String email = securityContext.getCallerPrincipal().getName();
            try {
                User user = userService.getUserByEmailLog(email);
                log.setUser(user);
            } catch (NoResultException e) {
            }
        }

        try {
            Object result = ctx.proceed();
            log.setSuccess(true);
            log.setMessage("Executed successfully");
            return result;
        } catch (Exception e) {
            log.setSuccess(false);
            log.setMessage("Exception: " + e.getMessage());
            throw e;
        } finally {
            logService.saveLog(log);
        }
    }
}
