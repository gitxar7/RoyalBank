package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.exception.TransactionException;
import com.ar7Enterprise.bank.core.service.InterestService;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class InterestTImerBean {

    @EJB
    private InterestService interestService;

    @Schedule(dayOfMonth = "1", hour = "0", minute = "0", second = "0", persistent = true)
    public void runInterestCalculation() {
        try {
            interestService.applyInterestToAllAccounts();
        } catch (Exception e) {
            throw new TransactionException("Could not apply interest calculation");
        }
    }
}
