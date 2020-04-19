package com.norwegian.pageobjects.explore;

import com.norwegian.basepageplatform.CommonImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vloparevich
 */
public class PortsImpl extends CommonImpl {
    private static final By INPUT_FIELD = By.xpath("//input[@id='searchbar']");
    private static final String PORTS_RESULT_LIST = "//li[@ng-repeat='port in matchingSearchPorts()']//a[contains(text(), '%s')]";
    private static final By PORT_OF_DEPARTURE = By.xpath("(//div[@id='map-info-clone']//li/div)[2]");
    private static final By IMAGE_OF_PORT = By.xpath("(//div[@title]/img[@src='/resources/images/icons/pin-port-of-departure.png'])[1]");
    private static final By MAP = By.xpath("//section[@id='portsLandingMap']");

    public PortsImpl(WebDriver driver) {
        super(driver);
    }

    public void searchPort(String portName) {
        sendKeys(INPUT_FIELD, portName);
        // First port in the list will be clicked
        clickElement(By.xpath(String.format(PORTS_RESULT_LIST, portName)));
    }

    public boolean isPortImageInTheMiddleOfTheMap() {
        WebElement map = getElementByLocator(MAP);
        WebElement portImage = getElementByLocatorWithPresence(IMAGE_OF_PORT);
        boolean isPortInTheMiddle = isCildInTheMiddleOfTheParentElement(map, portImage);
        return isPortInTheMiddle;
    }

    public String getLabelPortOfDeparture() {
        String label = getTextAsString(PORT_OF_DEPARTURE).replace("\n", " "); // "PORT OF DEPARTURE"
        return label;
    }
}
