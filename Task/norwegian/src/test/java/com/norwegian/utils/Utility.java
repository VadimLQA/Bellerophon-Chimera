package com.norwegian.utils;

import com.norwegian.basetestplatform.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author vloparevich
 */
public class Utility extends BaseTest {
    static String cwd = System.getProperty("user.dir");

    public static String timeStampLong() {
        DateFormat df = new SimpleDateFormat("MMMM/d/yyyy-HH.mm.ss.S");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }

    public static String timeStampShort() {
        return new SimpleDateFormat("MM.dd/HH-mm", Locale.US).format(new Date());
    }

    public static String logPath(String testName) {
        return System.getProperty("user.dir") + "/ProjectLogsShots/" + testName + "/";
    }

    public static void getScreenshot(String testName, String methodName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(logPath(testName) + timeStampLong() + "-methodName-" + methodName + "-screenshot.png"));
    }

    public static Object[][] excelDataParser(String excelFileName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir") +"/"+ excelFileName);
        XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workBook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = null;

        int lastRowIndex = sheet.getPhysicalNumberOfRows() - 1;
        int columnCount = row.getLastCellNum();

        Object[][] data = new Object[lastRowIndex][columnCount];

        for (int i = 0; i < lastRowIndex; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;
    }
}
