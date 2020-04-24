package com.norwegian.basepageplatform;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Integer.parseInt;


/**
 * @author vloparevich
 * This class can be used for implementing some shareable functionality such as menus, buttons etc...
 */
public class CommonImpl extends BasePage {
    public CommonImpl(WebDriver driver) {
        super(driver);
    }

    public boolean isCildInTheMiddleOfTheParentElement(WebElement parent, WebElement child) {
        Dimension parentDimensions = parent.getSize();
        Point childStartsAt = child.getLocation();
        int parentMiddle = parentDimensions.width / 2;
        int childWidth = parseInt(child.getAttribute("width"));
        int absoluteXcoordAtChildAxis = childStartsAt.getX() + childWidth / 2;
        return parentMiddle == absoluteXcoordAtChildAxis ? true : false;
    }

    public void getWideViewPort() {
        driver.manage().window().setSize(new Dimension(2000, 1024));
    }

    public void getSuperWideViewPort() {
        driver.manage().window().setSize(new Dimension(3000, 1024));
    }

    public void getStandardViewPort() {
        driver.manage().window().setSize(new Dimension(1280, 1024));
    }

}