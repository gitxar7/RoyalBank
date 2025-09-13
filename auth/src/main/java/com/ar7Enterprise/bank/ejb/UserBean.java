package com.ar7Enterprise.bank.ejb;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.annotation.Secured;
import com.ar7Enterprise.bank.core.exception.InvalidCredentialsException;
import com.ar7Enterprise.bank.core.model.Status;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.model.UserRole;
import com.ar7Enterprise.bank.core.service.AccountService;
import com.ar7Enterprise.bank.core.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserBean implements UserService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private AccountService accountService;

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Logged
    @Override
    public User getUserByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", User.class).setParameter(1, email).getSingleResult();
    }

    @Override
    public User getUserByEmailLog(String email) {
        return em.createNamedQuery("User.findByEmail", User.class).setParameter(1, email).getSingleResult();
    }

    @Logged
    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Logged
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Logged
    @Override
    @Secured(roles = {"USER", "ADMIN", "SUPER_ADMIN"})
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Logged
    @Override
    public boolean isUserValid(String email, String password) {
        User user = em.createNamedQuery("User.findByEmailAndPassword", User.class)
                .setParameter("email", email).setParameter("password", password).getSingleResult();
        return user != null;
    }

    @Logged
    @Override
    public void verifyUser(String email, String code) {
        User user = getUserByEmail(email);
        String verificationCode = user.getVerification();
        if (!user.getVerification().equals("1")) {
            if (verificationCode.equals(code)) {
                activateUser(email);
                accountService.createAccount(email);
            } else {
                throw new InvalidCredentialsException("Invalid or Expired Verification Code");
            }
        } else {
            throw new InvalidCredentialsException("User already verified");
        }
    }

    @Logged
    @Override
    public void activateUser(String email) {
        User user = getUserByEmail(email);
        if (!isUserActive(email)) {
            user.setStatus(Status.ACTIVE);
            user.setVerification("1");
            updateUser(user);
        } else {
            throw new InvalidCredentialsException("User already activated");
        }
    }

    @Logged
    @Override
    @Secured(roles = {"ADMIN", "SUPER_ADMIN"})
    public void deactivateUser(String email) {
        User user = getUserByEmail(email);
        if (isUserActive(email)) {
            user.setStatus(Status.INACTIVE);
            updateUser(user);
        } else {
            throw new InvalidCredentialsException("User already deactivated");
        }
    }

    @Logged
    @Override
    public boolean isUserActive(String email) {
        User user = getUserByEmail(email);
        if (user != null) {
            return user.getStatus().equals(Status.ACTIVE);
        }
        return false;
    }

    @Logged
    @Override
    @Secured(roles = {"SUPER_ADMIN"})
    public void addAnAdmin(String email) {
        User user = getUserByEmail(email);
        user.setUserRole(UserRole.ADMIN);
        updateUser(user);
    }
}
