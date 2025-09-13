package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.email.VerificationMail;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.provider.MailServiceProvider;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/request-verification")
public class VerificationRequest extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        User user = userService.getUserByEmail(email);
        if (!user.getVerification().equals("1")) {
            String verificationCode = UUID.randomUUID().toString();
            user.setVerification(verificationCode);
            userService.updateUser(user);
            VerificationMail mail = new VerificationMail(email, verificationCode);
            MailServiceProvider.getInstance().sendMail(mail);
        }

        resp.sendRedirect(req.getContextPath() + "/verify.jsp");
    }
}
