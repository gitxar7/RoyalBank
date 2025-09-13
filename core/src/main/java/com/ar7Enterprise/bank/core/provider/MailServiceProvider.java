package com.ar7Enterprise.bank.core.provider;

import com.ar7Enterprise.bank.core.email.Mailable;
import com.ar7Enterprise.bank.core.util.Env;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MailServiceProvider {
    private final Properties properties = new Properties();
    private Authenticator authenticator;
    private static MailServiceProvider instance;
    private ThreadPoolExecutor executor;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    private MailServiceProvider() {
        properties.put("mail.smtp.host", Env.getProperty("mail-trap.host"));
        properties.put("mail.smtp.port", Env.getProperty("mail-trap.port"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
    }

    public static MailServiceProvider getInstance() {
        if (instance == null) {
            instance = new MailServiceProvider();
        }
        return instance;
    }

    public void start() {
        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Env.getProperty("mail-trap.username"), Env.getProperty("mail-trap.password"));
            }
        };

        executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, queue,
                new ThreadPoolExecutor.AbortPolicy());
        executor.prestartAllCoreThreads();

        System.out.println("Starting Mail Service Provider");
    }

    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void sendMail(Mailable mail) {
        queue.add(mail);
    }

    public Properties getProperties() {
        return properties;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }
}
