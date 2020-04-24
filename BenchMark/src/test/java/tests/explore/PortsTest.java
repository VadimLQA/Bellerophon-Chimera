package tests.explore;


import basetest.UserSetUpTest;
import com.norwegian.explore.PortsImpl;
import com.norwegian.navigation.MainNavigationBarImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.DataProviders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author vloparevich
 */
public class PortsTest extends UserSetUpTest {

    private MainNavigationBarImpl navMenu;
    private PortsImpl portPage;


    @BeforeClass
    public void setUp() {
        navMenu = new MainNavigationBarImpl(driver);
        navMenu.getMainBarOption(MainNavigationBarImpl.Tab.EXPLORE);
        navMenu.getSubBarOption(MainNavigationBarImpl.SubTab.PORTS);

        portPage = new PortsImpl(driver);
    }

    @Test(dataProvider = "ports-name", dataProviderClass = DataProviders.class,
            description = "" +
                    "Verify that port of destination is displayed in the middle of the map " +
                    "AND verify the label of the Port of Destination")
    public void testDisplayingDeparturePort(String portName, String labelOfPortOfDep) {
        portPage.searchPort(portName);
        logger.info("*** verify that image is in the middle of the map ***");
        assertTrue(portPage.isPortImageInTheMiddleOfTheMap(), "The port is not in the middle of the map");
        logger.info("*** verify label of port departure ***");
        assertEquals(portPage.getLabelPortOfDeparture(), labelOfPortOfDep, "Labels did not match");
    }

    @AfterMethod
    public void afterMethodSetUp() {
        portPage.refreshPage();
    }
}