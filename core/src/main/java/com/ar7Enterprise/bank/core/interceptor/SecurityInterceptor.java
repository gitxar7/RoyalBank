package com.ar7Enterprise.bank.core.interceptor;

import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.exception.UnauthorizedException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;

import java.util.Arrays;
import java.util.logging.Logger;

@Secured
@Interceptor
@Priority(1)
public class SecurityInterceptor {
    @Inject
    private SecurityContext securityContext;

    private static final Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());

    @AroundInvoke
    public Object checkSecurity(InvocationContext ctx) throws Exception {
        Secured secured = ctx.getMethod().getAnnotation(Secured.class);
        if (secured == null) {
            secured = ctx.getTarget().getClass().getAnnotation(Secured.class);
        }

        if (secured != null) {
            String[] allowedRoles = secured.roles();
            if (securityContext == null || securityContext.getCallerPrincipal() == null) {
                throw new UnauthorizedException("Access denied: user not authenticated.");
            }

            boolean authorized = Arrays.stream(allowedRoles)
                    .anyMatch(securityContext::isCallerInRole);

            if (!authorized) {
                logger.warning("Unauthorized access attempt by user: " +
                        securityContext.getCallerPrincipal().getName() +
                        " on method: " + ctx.getMethod().getName());

                throw new UnauthorizedException("Access denied: insufficient permissions.");
            }
        }

        return ctx.proceed();
    }
}
