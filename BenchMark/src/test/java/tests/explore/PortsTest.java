package tests.explore;


import basetest.UserSetUpTest;
import com.norwegian.explore.PortsImpl;
import com.norwegian.navigation.MainNavigationBarImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;
import testdata.DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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


    private static final String YAML_DATA =
            "nameOfThePort: Honolulu\n" +
                    "labelOfThePort: Port Of Departure";

    private static final String YAML_FILE = "src/test/java/testdata/ports.yaml";

    @DataProvider(name = "yaml-data")
    private Object[][] parseYaml() {
        Yaml yaml = new Yaml();
        Map<String, String> map = new HashMap();
        map = yaml.load(YAML_DATA);
        return new Object[][]{map.values().toArray()};
    }

    @DataProvider(name = "yaml-parser")
    private Object[][] parseYAMLFile() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream iStream = new FileInputStream(new File(YAML_FILE));
        ArrayList<Map<String, String>> portsCollection = yaml.load(iStream);

        String[][] ports2DArray = new String[portsCollection.size()][2];
        int i = 0;
        for (Map<String, String> portsMap : portsCollection) {
            Collection<String> portsValuesCollection = portsMap.values();
            for (Object portValue : portsValuesCollection) {
                Map<String, String> portMap = (Map<String, String>) portValue;
                for (Map.Entry<String, String> port : portMap.entrySet()) {
                    if (port.getKey().equals("nameOfThePort")) {
                        ports2DArray[i][0] = port.getValue();
                    }
                    if (port.getKey().equals("labelOfThePort")) {
                        ports2DArray[i][1] = port.getValue();
                    }
                }
            }
            i++;
        }
        return ports2DArray;
    }

    @Test(dataProvider = "yaml-data")
    public void testDisplayingDeparturePortYAML(String portName, String labelOfPortOfDep) {
        portPage.searchPort(portName);
        logger.info("*** verify that image is in the middle of the map ***");
        assertTrue(portPage.isPortImageInTheMiddleOfTheMap(), "The port is not in the middle of the map");
        logger.info("*** verify label of port departure ***");
        assertEquals(portPage.getLabelPortOfDeparture(), labelOfPortOfDep, "Labels did not match");
    }

    @Test(dataProvider = "yaml-parser")
    public void testDisplayingDeparturePortParserYAML(String portName, String labelOfPortOfDep) {
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