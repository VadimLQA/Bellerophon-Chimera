package com.employee.tests;

import com.employee.basetest.BaseTest;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC002_Get_Single_Employee_Record extends BaseTest {

    @BeforeClass
    public void beforeClassSetUp() throws InterruptedException {
        logger.info("**********Started TC002_Get_Single_Employee_Record ***********");

        response = httpRequest.request(Method.GET, "/employee/" + empID);
        asyncWait(response);
    }

    @Test
    public void testResponseBody() {
        logger.info("********** testGetEmployee **********");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        assertEquals(responseBody.contains(empID), true);
    }

    @Test
    public void testStatusCode() {
        logger.info("********** testStatusCode **********");
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        assertEquals(statusCode == 200, true);
    }

    @Test
    public void testResponseTime(){
        long responseTime = response.getTime();
        assertTrue(responseTime < 3000);
    }

    @Test
    public void testStatusLine(){
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        assertTrue(statusLine.equals("HTTP/1.1 200 OK"));
    }

    private void logInfo(String testName) {
        logger.info("********** " + testName + " **********");
    }

    @AfterClass
    public void afterClassTearDown(){
        logInfo("FINISHED: TC001_Get_All_Employees");
    }
}
