package com.employee.tests;

import com.employee.basetest.BaseTest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC001_Get_All_Employees extends BaseTest {
    static String thisClassName = TC001_Get_All_Employees.class.getSimpleName();

    @BeforeClass
    public void beforeClassSetUp() {
        logger.info("**********" + thisClassName + "***********");
        response = httpRequest.request(Method.GET, "/employees");
        System.out.println("body:: " + response.getBody().asString());
    }

    @Test
    public void listAllHeaders() {
        Headers headers = response.getHeaders();
        for (Header header : headers) {
            System.out.println("{ \"" + header.getName() + "\": \"" + header.getValue() + "\" }");
        }
    }

    @Test
    public void testResponseBody() {
        logger.info("********** testGetAllEmployees **********");
        String responseBody = response.getBody().asString();
        logger.info("Response Body==>" + responseBody);
        assertTrue(responseBody != null);
    }

    @Test
    public void testStatusCode() {
        logger.info("********** testStatusCode **********");
        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200);
    }

    @Test
    public void testResponseTime() {
        logger.info("********** testResponseTime **********");
        long responseTime = response.getTime(); // getting response time
        logger.info("Response time is: " + responseTime);
        if (responseTime > 2000) {
            logger.warn("ResponseTime is greater than 2000ms");
        }
        assertTrue(responseTime < 2500);
    }

    @Test
    public void testStatuLine() {
        logger.info("********** testStatuLine **********");
        String statusLine = response.getStatusLine();
        assertTrue(statusLine.equals("HTTP/1.1 200 OK"));
    }

    @Test
    public void testContentType() {
        logger.info("********** testContentType **********");
        String contentType = response.getContentType();
        assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    public void testServerType() {
        logger.info("********** testServerType **********");
        String serverType = response.header("Server");
        assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    public void testContentEncoding() {
        logInfo("testContentEncoding");
        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content-Encoding=> " + contentEncoding);
        assertEquals(contentEncoding, "gzip");
    }

    @Test
    public void testContentLength() {

        logInfo("testContentLength");
        String contentLength = response.header("Content-Length");
        logger.info("Content-Length=> " + contentLength);
        assertTrue(Integer.parseInt(contentLength) > 100);
    }

    @Test
    public void testCookies() {
        logInfo("testCookies");
        String cookies = response.getCookie("PHPSESSIONID");
    }

    private void logInfo(String testName) {
        logger.info("********** " + thisClassName + "." + testName + " **********");
    }


    @AfterClass
    public void afterClassTearDown() {
        logInfo("FINISHED: TC001_Get_All_Employees");
    }
}
