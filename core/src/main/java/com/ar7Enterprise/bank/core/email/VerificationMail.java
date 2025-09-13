package com.ar7Enterprise.bank.core.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;

public class VerificationMail extends Mailable {

    private final String to;
    private final String verificationCode;

    public VerificationMail(String to, String verificationCode) {
        this.to = to;
        this.verificationCode = verificationCode;
    }

    @Override
    public void build(Message message) throws MessagingException {
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Verification Mail");
        String mailContent =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; padding: 20px;'>" +
                        "<h2 style='color: #512DA8;'>Royal Bank</h2>" +
                        "<p style='font-size: 16px;'>Your Verification Code is:</p>" +
                        "<div style='background: #f4f4f4; padding: 10px; border: 1px solid #ccc; width: fit-content; font-size: 18px;'>" +
                        "<code>" + verificationCode + "</code>" +
                        "</div>" +
                        "<p style='color: gray; font-size: 12px;'>Select the code above and press Ctrl+C (or tap and hold to copy on mobile)</p>" +
                        "</body>" +
                        "</html>";

        message.setContent(mailContent, "text/html; charset=utf-8");
    }
}
