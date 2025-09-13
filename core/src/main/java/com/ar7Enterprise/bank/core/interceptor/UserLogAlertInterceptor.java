package com.ar7Enterprise.bank.core.interceptor;

import com.ar7Enterprise.bank.core.annotation.UserLogAlert;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@UserLogAlert
@Interceptor
public class UserLogAlertInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        System.out.println("UserLogAlertInterceptor");
        return ic.proceed();
    }
}
