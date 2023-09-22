package com.jacobwoolbright;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static String botToken;
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    static {
        try (FileInputStream fis = new FileInputStream("config.cfg")) {
            Properties prop = new Properties();
            prop.load(fis);

            botToken = prop.getProperty("botToken");
            dbUrl = prop.getProperty("dbUrl");
            dbUsername = prop.getProperty("dbUsername");
            dbPassword = prop.getProperty("dbPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getters
    public static String getBotToken() {
        return botToken;
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
}
