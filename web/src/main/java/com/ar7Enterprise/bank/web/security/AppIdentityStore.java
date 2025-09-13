package com.ar7Enterprise.bank.web.security;

import com.ar7Enterprise.bank.core.annotation.Logged;
import com.ar7Enterprise.bank.core.exception.LoginFailedException;
import com.ar7Enterprise.bank.core.model.Status;
import com.ar7Enterprise.bank.core.model.User;
import com.ar7Enterprise.bank.core.service.UserService;
import com.oracle.wls.shaded.org.apache.bcel.verifier.exc.VerificationException;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;

@ApplicationScoped
public class AppIdentityStore implements IdentityStore {
    @EJB
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;
            if (userService.isUserValid(upc.getCaller(), upc.getPasswordAsString())) {
                User user = userService.getUserByEmail(upc.getCaller());
                if (user.getStatus().equals(Status.ACTIVE)) {
                    return new CredentialValidationResult(user.getEmail(), Set.of(user.getUserRole().name()));
                } else {
                    throw new LoginFailedException("User is not active");
                }
            }
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
