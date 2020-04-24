package com.norwegian.data;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static com.norwegian.utils.Utility.excelDataParser;

/**
 * @author vloparevich
 */
public class DataProviders {

    @DataProvider(name = "ports-name")
    public Object[][] portsProvider() throws IOException {
        return excelDataParser("ports.xlsx");
    }
}
