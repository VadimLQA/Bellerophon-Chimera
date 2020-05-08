package testdata;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static utilities.Utility.excelParser;

/**
 * @author vloparevich
 **/
public class DataProviders {
    @DataProvider(name="employees_data")
    public Object[][] emplProvider() throws IOException {
        return excelParser("employeesData.xlsx");
    }

}
