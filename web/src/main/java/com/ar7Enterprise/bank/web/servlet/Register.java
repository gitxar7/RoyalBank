package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.email.VerificationMail;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.provider.MailServiceProvider;
import com.ar7Enterprise.bank.core.service.UserService;
import com.ar7Enterprise.bank.core.util.Encryption;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/register")
public class Register extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");

        String encrypt = Encryption.encrypt(password);

        String verificationCode = UUID.randomUUID().toString();
        VerificationMail mail = new VerificationMail(email, verificationCode);
        MailServiceProvider.getInstance().sendMail(mail);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(encrypt);
        user.setVerification(verificationCode);

        userService.addUser(user);

        resp.sendRedirect(req.getContextPath() + "/verify.jsp");
    }
}
