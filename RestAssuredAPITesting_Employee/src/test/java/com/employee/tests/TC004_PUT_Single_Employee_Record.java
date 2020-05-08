package com.employee.tests;

import com.employee.basetest.BaseTest;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utilities.RestUtils.*;

public class TC004_PUT_Single_Employee_Record extends BaseTest {
    String name = empName();
    String salary = empSal();
    String empAge = empAge();

    @BeforeClass
    public void createEmployee() throws InterruptedException {
        logger.info("**********Started TC002_Get_Single_Employee_Record ***********");

        // JSONObject
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("salary", salary);
        requestParams.put("age", empAge);

        httpRequest.body(requestParams.toJSONString());

        httpRequest.header("Content-Type", "application/json");
        response = httpRequest.request(Method.PUT, "/update/" + empID);
       // asyncWait(response);
    }

    @Test
    public void testResponseBody() {
        logger.info("********** testResponseBody **********");
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        String status = response.jsonPath().get("status");
        assertTrue(status.equals("success"));
    }

    @Test
    public void testStatus(){
        logger.info("**** testStatusSuccess ****");
        String successStatus = response.jsonPath().get("status");
        assertEquals(successStatus, "success");

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
        logger.info("********** testResponseTime **********");
        long responseTime = response.getTime();
        assertTrue(responseTime < 3000);
    }

    @Test
    public void testStatusLine(){
        logger.info("********** testStatusLine **********");
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        assertTrue(statusLine.equals("HTTP/1.1 200 OK"));
    }

    @Test
    public void testPragma(){
        logger.info("********** testPragma **********");
        String contentEncoding = response.header("Pragma");
        assertTrue(contentEncoding.equals("no-cache"));
    }

    @Test
    public void testContentLength(){
        logger.info("**** testContentLength ****");
        String contentLength = response.header("Content-Length");
        assertTrue(Integer.parseInt(contentLength) < 50);
    }

    private void logInfo(String testName) {
        logger.info("********** " + testName + " **********");
    }

    @AfterClass
    public void afterClassTearDown(){
        logInfo("FINISHED: TC001_Get_All_Employees");
    }
}
