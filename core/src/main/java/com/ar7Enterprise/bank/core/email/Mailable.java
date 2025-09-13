package com.ar7Enterprise.bank.core.email;

import com.ar7Enterprise.bank.core.provider.MailServiceProvider;
import com.ar7Enterprise.bank.core.util.Env;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public abstract class Mailable implements Runnable {

    private final MailServiceProvider provider;

    public Mailable() {
        provider = MailServiceProvider.getInstance();
    }

    @Override
    public void run() {
        try {
            Session session = Session.getInstance(provider.getProperties(), provider.getAuthenticator());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Env.getProperty("app.email")));
            build(message);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void build(Message message) throws MessagingException;
}
