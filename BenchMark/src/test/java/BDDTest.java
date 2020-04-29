import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author vloparevich
 **/

@CucumberOptions(features = {"src/test/features/ShoreExcursionImpl.feature"}, strict = true)
public class BDDTest extends AbstractTestNGCucumberTests {
    //  public static WebDriver driver;
    /*@BeforeClass
    public void setUP() {
       // System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
     //   driver.get("https://google.com");
    }*/

}
