package com.ar7Enterprise.bank.core.service;

import com.ar7Enterprise.bank.core.model.Transaction;
import com.ar7Enterprise.bank.core.model.User;
import jakarta.ejb.Remote;

import java.util.List;

public interface ReportService{
    public byte[] generateMonthlyTransactionReport(String email);
    public byte[] generatePDF(List<Transaction> transactions, User user);
}
