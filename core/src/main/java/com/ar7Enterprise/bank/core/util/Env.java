package com.ar7Enterprise.bank.core.util;

import java.io.InputStream;
import java.util.Properties;

public class Env {
    private static Properties properties = new Properties();
    static {
        try {
            InputStream resource = Env.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
