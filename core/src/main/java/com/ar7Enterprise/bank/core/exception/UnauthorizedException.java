package com.ar7Enterprise.bank.core.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
