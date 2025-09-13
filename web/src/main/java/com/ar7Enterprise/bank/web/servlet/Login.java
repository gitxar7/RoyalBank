package com.ar7Enterprise.bank.web.servlet;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.email.LoginAlertMail;
import com.ar7Enterprise.bank.core.email.VerificationMail;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.provider.MailServiceProvider;
import com.ar7Enterprise.bank.core.service.UserService;
import com.ar7Enterprise.bank.core.util.Encryption;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        AuthenticationParameters credential = AuthenticationParameters.withParams()
                .credential(new UsernamePasswordCredential(email, Encryption.encrypt(password)));

        AuthenticationStatus status = securityContext.authenticate(req, resp, credential);
        System.out.println("Authentication status: " + status);

        if (status == AuthenticationStatus.SUCCESS) {
            String ip = req.getRemoteAddr();
            LocalDateTime currentDateTime = LocalDateTime.now();

            LoginAlertMail mail = new LoginAlertMail(email, currentDateTime.toString(), ip);
//            MailServiceProvider.getInstance().sendMail(mail);
            User user = userService.getUserByEmail(email);
            user.setLastLoginDate(currentDateTime);
            userService.updateUser(user);

            req.getSession().setAttribute("user", email);
            resp.sendRedirect(req.getContextPath() + "/user/index.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
//            throw  new LoginFailedException("Invalid credentials");
        }
    }
}
