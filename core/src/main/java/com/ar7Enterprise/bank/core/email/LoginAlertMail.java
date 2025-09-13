package com.ar7Enterprise.bank.core.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;

public class LoginAlertMail extends Mailable {

    private final String email;
    private final String date;
    private final String ip;

    public LoginAlertMail(String email, String date, String ip) {
        this.email = email;
        this.date = date;
        this.ip = ip;
    }

    @Override
    public void build(Message message) throws MessagingException {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Security Mail");
        String mailContent = "<html>" +
                "<body style='font-family: Arial, sans-serif; padding: 20px;'>" +
                "<h2 style='color: #512DA8;'>Royal Bank - Login Alert</h2>" +
                "<p style='font-size: 16px;'>We detected a login to your account with the following details:</p>" +

                "<table style='border-collapse: collapse; margin-top: 10px;'>" +
                "  <tr>" +
                "    <td style='padding: 8px; font-weight: bold;'>Date & Time:</td>" +
                "    <td style='padding: 8px;'>" + date + "</td>" +
                "  </tr>" +
                "  <tr>" +
                "    <td style='padding: 8px; font-weight: bold;'>IP Address:</td>" +
                "    <td style='padding: 8px;'>" + ip + "</td>" +
                "  </tr>" +
                "</table>" +

                "<p style='margin-top: 20px; font-size: 14px; color: #555;'>If this was you, you can safely ignore this email.<br>" +
                "If you did not perform this login, please secure your account immediately.</p>" +

                "<p style='font-size: 12px; color: #888;'>This is an automated message. Please do not reply.</p>" +
                "</body>" +
                "</html>";
        ;

        message.setContent(mailContent, "text/html; charset=utf-8");
    }
}
