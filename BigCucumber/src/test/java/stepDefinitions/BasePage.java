package stepDefinitions;

import basetest.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author vloparevich
 **/
public class BasePage extends CommonImpl {

    public String getDriverInstanceName() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        return cap.getBrowserName().toLowerCase();
    }

    protected String getAttributeValue(By locator, String attributeName) {
        waitForElementExplicitly(locator);
        return getElementByLocator(locator).getAttribute(attributeName);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickElement(By locator) {
        if (getDriverInstanceName().equals("chrome")) {
            waitForElementExplicitly(locator);
            actions.moveToElement(getElementByLocator(locator)).click().build().perform();
        }
    }

    private void waitForElementExplicitly(By waitLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(waitLocator));
    }

    private void waitForElementPresentExplicitly(By waitLocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(waitLocator));
    }

    public void sendKeys(By locator, String keys) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementExplicitly(locator);
        actions.moveToElement(getElementByLocator(locator)).click().sendKeys(keys).build().perform();
    }

    protected WebElement getElementByLocator(By locator) {
        waitForElementExplicitly(locator);
        return driver.findElement(locator);
    }

    protected WebElement getElementByLocatorWithPresence(By locator) {
        waitForElementPresentExplicitly(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> getElementsByLocator(By locator) {
        waitForElementExplicitly(locator);
        return driver.findElements(locator);
    }

    protected List<WebElement> getElementsWithNoWaitByLocator(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Waits for the element disappearance in case of overlapping (not working for
     * Safari- known driver issue)
     */
    protected void waitForElementToVanish(By element) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No element presented");
        }
    }

    public String getTextAsString(By locator) {
        return getElementByLocator(locator).getText();
    }
}
