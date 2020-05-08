package runners;

import basetest.BaseTest;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

/**
 * @author vloparevich
 **/
@CucumberOptions(
        features = {"src/test/java/features/SearchingPort.feature"},
        glue = {"stepDefinitions"},
        strict = true,
        plugin={"pretty", "html:target/cucumber-report", "json:target/cucumber.json"})
public class LoginTest extends BaseTest {

    @BeforeClass
    public void beforeClassSetUp() {
        System.out.println("From runner of runners.LoginTest");
    }
}
