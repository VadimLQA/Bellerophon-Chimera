package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author vloparevich
 **/
public class LandingPageImpl extends BasePage {
    String currentClassName = LandingPageImpl.class.getSimpleName();

    private By loginButton = By.xpath("//*[@title='Log in']");

    @Given("^user is on landing page$")
    public void user_is_on_landing_page() {
        Assert.assertTrue(driver.findElements(loginButton).size() > 0);
    }
}
