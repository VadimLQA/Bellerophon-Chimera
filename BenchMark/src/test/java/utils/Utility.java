package utils;

import basetest.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

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
        FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/testdata/" + excelFileName);
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


    private static Connection getDBConnection(String host, String port, String dbName) throws ClassNotFoundException, SQLException {
        // JDBC API is available in two packages java.sql, core API and javax.sql JDBC optional packages
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName + "", "root", "root");
        return connection;
    }

    public static List<Credentials> getResultSet() throws SQLException, ClassNotFoundException {
        List list = new ArrayList<>();
        Connection con = getDBConnection("localhost", "3306", "qadbt");
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select * from employeeinfo where location = \"florida\" or location = \"New York\";");
        while (rs.next()) {
            String login = rs.getString("userName");
            String password = rs.getString("password");
            list.add(new Credentials(login, password));
        }
        return list;
    }

    private static class Credentials {
        private String login;
        private String password;

        public Credentials(String login, String pass) {
            this.login = login;
            this.password = pass;
        }

        public String getLoginName() {
            return this.login;
        }

        public String getPassword() {
            return this.password;
        }
    }

    public static void INITIALMETHOD() throws SQLException, ClassNotFoundException {
       /* /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
        String host = "localhost";
        String port = "3306";// set by default
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String datbaseName = "qadbt";
        //1st connecting my DB
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + datbaseName + "","root", "root");
        //2nd Creates a Statement object for sending SQL statements to the database.
        Statement s = con.createStatement();
        //3rd Executing the SQL query
        ResultSet rs = s.executeQuery("select * from employeeinfo where location = \"florida\" or location = \"New York\";");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        while(rs.next()){  // Moves the cursor forward one row from its current position. A ResultSet cursor is initially positioned before the first row (or just put rs.next() without while IF I HAVEONLY ONE ROW OF RECORD SELECTED)

            System.out.println(rs.getString("location"));
            System.out.println(rs.getInt("age"));

            login = rs.getString("location");
            password = rs.getString("age");

            //Test (Left like this for observing. It should be in the @Test of course)
            driver.findElement(loginField).sendKeys(login);
            driver.findElement(passwordField).sendKeys(password);
        }*/
    }

    public static int getResponseCode(String link) {
        URL url;
        HttpURLConnection con = null;
        Integer responsecode = 0;
        try {
            url = new URL(link);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(100);
            responsecode = con.getResponseCode();
        } catch
        (Exception e) {
            // skip
        } finally {
            if (con != null)
                con.disconnect();
        }
        return responsecode;
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

}
