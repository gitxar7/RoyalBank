package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.model.Account;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.TransactionService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/fund-transfer")
public class FundTransfer extends HttpServlet {

    @EJB
    private AccountService accountService;

    @EJB
    private TransactionService transactionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("user").toString();
        String destinationAccountNumber = req.getParameter("destinationAccountNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        if (email != null) {
            Account account = accountService.getAccountByEmail(email);
            String sourceAccountNumber = account.getAccountNumber();
            transactionService.transferAmount(sourceAccountNumber, destinationAccountNumber, amount);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/user/index.jsp");
    }
}
