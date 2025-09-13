package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.model.ScheduledTransaction;
import com.ar7Enterprise.bank.core.model.TransactionType;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.ScheduledTransactionService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/user/schedule-transfer")
public class ScheduleFundTransfer extends HttpServlet {

    @EJB
    private UserService userService;
    @EJB
    private AccountService accountService;
    @EJB
    private ScheduledTransactionService scheduledTransactionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("user").toString();
        String destinationAccountNumber = req.getParameter("destinationAccountNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        LocalDate date = LocalDate.parse(req.getParameter("scheduledDate"));

        if (email != null) {
            User from = userService.getUserByEmail(email);
            User to = accountService.getAccountByAccountNumber(destinationAccountNumber).getUser();
            ScheduledTransaction transaction = new ScheduledTransaction(from, to, amount, TransactionType.TRANSFER, date);
            scheduledTransactionService.addScheduledTransaction(transaction);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

//        req.setAttribute("msg", "Transfer scheduled successfully!");
//        req.getRequestDispatcher("/user/schedule-transfer.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/user/index.jsp");
    }
}
