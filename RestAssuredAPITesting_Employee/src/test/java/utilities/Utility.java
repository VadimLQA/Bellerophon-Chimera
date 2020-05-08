package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author vloparevich
 **/
public class Utility {

    public static Object[][] excelParser(String excelFileName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir") + "//src/test/java/testdata/" + excelFileName);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = null;

        int rowCount = sheet.getPhysicalNumberOfRows() - 1;
        int columnCount = row.getLastCellNum();
        Object[][] resultObject = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                row = sheet.getRow(i + 1);
                cell = row.getCell(j);
                resultObject[i][j] = formatter.formatCellValue(cell);/*formatter.formatCellValue(cell);*/
            }
        }
        workbook.close();
        excelFile.close();
        return resultObject;

    }
}
