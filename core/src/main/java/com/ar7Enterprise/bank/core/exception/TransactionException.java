package com.ar7Enterprise.bank.core.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}
