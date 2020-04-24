package com.norwegian.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author vloparevich
 */
public class LoginImpl extends CommonImpl {
    private static final By LOGIN_FIELD = By.xpath("//form//dd/input[@name='userName']");
    private static final By PASSWORD_FIELD = By.xpath("//form//dd/input[@name='password']");
    private static final By LOGIN_BTN = By.xpath("//button[text() = 'Log In']");
    private static final By REGISTER_BTN = By.cssSelector("div a[class='btn-cta btn-secondary']");

    public LoginImpl(WebDriver driver) {
        super(driver);
    }

    public void login(boolean isRequired) throws IOException {
        if (isRequired) {
            FileInputStream f = new FileInputStream(localPropertiesPath);
            Properties localProperties = new Properties();
            localProperties.load(f);
            String userName = localProperties.getProperty("userName");
            String password = localProperties.getProperty("userPassword");
            sendKeys(LOGIN_FIELD, userName);
            sendKeys(PASSWORD_FIELD, password);
            clickElement(LOGIN_BTN);
        }
    }
}
