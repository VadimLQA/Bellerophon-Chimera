package testdata;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static utils.Utility.excelDataParser;

/**
 * @author vloparevich
 **/
public class DataProviders {
    @DataProvider(name = "ports-name")
    public Object[][] portsProvider() throws IOException {
        return excelDataParser("ports.xlsx");
    }
}
