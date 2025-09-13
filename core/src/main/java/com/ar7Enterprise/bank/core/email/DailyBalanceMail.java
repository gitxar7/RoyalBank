package com.ar7Enterprise.bank.core.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;

public class DailyBalanceMail extends Mailable {

    private final String email;
    private final String date;
    private final double balance;
    private final String account;

    public DailyBalanceMail(String email, String date, String account, double balance) {
        this.email = email;
        this.date = date;
        this.balance = balance;
        this.account = account;
    }

    @Override
    public void build(Message message) throws MessagingException {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Account Balance");
        String mailContent = "<html>" +
                "<body style='font-family: Arial, sans-serif; padding: 20px;'>" +
                "<h2 style='color: #512DA8;'>Royal Bank - Daily Balance Update</h2>" +

                "<p style='font-size: 16px;'>Here is your account summary for <strong>" + date + "</strong>:</p>" +

                "<table style='border-collapse: collapse; margin-top: 10px;'>" +
                "  <tr>" +
                "    <td style='padding: 8px; font-weight: bold;'>Account Number:</td>" +
                "    <td style='padding: 8px;'>" + account + "</td>" +
                "  </tr>" +
                "  <tr>" +
                "    <td style='padding: 8px; font-weight: bold;'>Available Balance:</td>" +
                "    <td style='padding: 8px;'>LKR " + balance + "</td>" +
                "  </tr>" +
                "</table>" +

                "<p style='margin-top: 20px; font-size: 14px; color: #555;'>This is your daily automated balance update.</p>" +

                "<p style='font-size: 12px; color: #888;'>Thank you for banking with Royal Bank.</p>" +
                "</body>" +
                "</html>";
        ;
        message.setContent(mailContent, "text/html; charset=utf-8");
    }
}
