package com.jacobwoolbright;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static WebDriverManager instance;

    private final WebDriver driver;

    private WebDriverManager(){
        driver = new ChromeDriver();
    }

    public static WebDriverManager getInstance() {
        if(instance == null){
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
