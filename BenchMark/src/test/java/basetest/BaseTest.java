package basetest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author vloparevich
 */
public class BaseTest {
    public static final String localPropertiesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\env-qa.properties";
    public static WebDriver driver;
    public static Logger logger;
    private String explorer;


    private String url;
    private FileInputStream f;
    protected static Properties prop;


    @BeforeSuite
    public void baseSetUp() throws IOException {
        prop = new Properties();
        f = new FileInputStream(localPropertiesPath);
        prop.load(f);
        url = prop.getProperty("basePageUrl");
        explorer = prop.getProperty("browser");

        if (driver == null) {
            this.driver = WebDriverFactory.getDriver(explorer.toLowerCase());
        }

        driver.manage().window().setSize(new Dimension(1280, 1024));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @BeforeClass
    public void beforeClassBaseSetUp() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/Log4j.properties");
        logger = Logger.getLogger(this.getClass().getName());
        logger.setLevel(Level.DEBUG);
    }

    @AfterSuite
    public void afterSuitePerformed() {
        driver.quit();
        driver = null;
    }
}