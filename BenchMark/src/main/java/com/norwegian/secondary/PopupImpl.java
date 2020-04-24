package com.norwegian.secondary;

import com.norwegian.base.CommonImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class PopupImpl extends CommonImpl {
    private static final By POPUP_CLOSE_BUTTON = By.xpath("//map/area[@alt='close']");

    public PopupImpl(WebDriver driver) {
        super(driver);
    }

    public void destroyPopUp() {
        try {
            clickElement(POPUP_CLOSE_BUTTON);
        } catch (TimeoutException e) {
            System.out.println("Popup window did not appear...\nTest is in progress!");
        }
    }


}
