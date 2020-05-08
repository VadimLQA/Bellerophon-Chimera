package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

/**
 * @author vloparevich
 **/
public class PortsPageImpl extends BasePage {
    String currentClassName = PortsPageImpl.class.getSimpleName();

    private static final By INPUT_FIELD = By.xpath("//input[@id='searchbar']");
    private static final String PORTS_RESULT_LIST = "//li[@ng-repeat='port in matchingSearchPorts()']//a[contains(text(), '%s')]";
    private static final By PORT_OF_DEPARTURE = By.xpath("(//div[@id='map-info-clone']//li/div)[2]");
    private static final By IMAGE_OF_PORT = By.xpath("(//div[@title]/img[@src='/resources/images/icons/pin-port-of-departure.png'])[1]");
    private static final By MAP = By.xpath("//section[@id='portsLandingMap']");


    public void searchPort(String portName) {
        sendKeys(INPUT_FIELD, portName);
        // First port in the list will be clicked
        clickElement(By.xpath(String.format(PORTS_RESULT_LIST, portName)));
    }

    public boolean isPortImageInTheMiddleOfTheMap() {
        WebElement map = getElementByLocator(MAP);
        WebElement portImage = getElementByLocatorWithPresence(IMAGE_OF_PORT);
        boolean isPortInTheMiddle = isChildInTheMiddleOfTheParentElement(map, portImage);
        return isPortInTheMiddle;
    }

    public String getLabelPortOfDeparture() {
        String label = getTextAsString(PORT_OF_DEPARTURE).replace("\n", " "); // "PORT OF DEPARTURE"
        return label;
    }

    @And("^user searches the port of \"([^\"]*)\"$")
    public void user_searches_port(String firstGroup) {
        searchPort(firstGroup);
    }

    @Then("port is in the middle of the map")
    public void isPortInTheMiddleOfTheMap() {
        logger.debug(currentClassName + ":: Verifying the position of the port");
        assertTrue(isPortImageInTheMiddleOfTheMap());
    }

    @Then("^label of the port departure is as \"([^\"]*)\"$")
    public void verifyLabelOfPortOfDep(String label) {
        logger.debug(currentClassName + ":: Verifying the label of the port");
        assertTrue(getLabelPortOfDeparture().equals(label), "Label does not match");
    }
}
