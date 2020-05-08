package com.employee.tests;

import com.employee.basetest.BaseTest;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC005_DELETE_Single_Employee_Record extends BaseTest {

    @BeforeClass
    public void deleteEmployee() {
        logger.info("**********Started TC005_DELETE_Single_Employee_Record ***********");
        response = httpRequest.request(Method.GET, "/employees");
        String bodyAsString = response.getBody().asString();

        System.out.println(bodyAsString);

        // Capturing ID
        JsonPath jsonPathEvaluator = response.jsonPath();
        String empID = jsonPathEvaluator.get("data[0].id");

        response = httpRequest.request(Method.DELETE, "/delete/" + empID);
        asyncWait(response);
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
    public void testStatus() {
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
    public void testResponseTime() {
        logger.info("********** testResponseTime **********");
        long responseTime = response.getTime();
        assertTrue(responseTime < 3000);
    }

    @Test
    public void testStatusLine() {
        logger.info("********** testStatusLine **********");
        String statusLine = response.getStatusLine();
        System.out.println(statusLine);
        assertTrue(statusLine.equals("HTTP/1.1 200 OK"));
    }

    @Test
    public void testPragma() {
        logger.info("********** testPragma **********");
        String contentEncoding = response.header("Pragma");
        assertTrue(contentEncoding.equals("no-cache"));
    }

    @Test
    public void testContentLength() {
        logger.info("**** testContentLength ****");
        String contentLength = response.header("Content-Length");
        assertTrue(Integer.parseInt(contentLength) < 85);
    }

    private void logInfo(String testName) {
        logger.info("********** " + testName + " **********");
    }

    @AfterClass
    public void afterClassTearDown() {
        logInfo("FINISHED: TC005_DELETE_Single_Employee");
    }
}
