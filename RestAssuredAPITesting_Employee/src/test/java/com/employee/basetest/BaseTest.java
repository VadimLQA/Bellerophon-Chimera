package com.employee.basetest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class BaseTest {
    public static RequestSpecification httpRequest;
    public static Response response;
    public static String empID = "55769"; // Hard coded

    public Logger logger;

    public static void asyncWait(Response response) {
        await().atMost(3000, TimeUnit.MILLISECONDS).until(() -> response.getStatusCode() >= 200);
    }

    @BeforeClass
    public void baseSetUp() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        logger = Logger.getLogger("RestAssuredAPITesting  "); // added Logger;
        PropertyConfigurator.configure("Log4j.properties"); // added logger
        logger.setLevel(Level.DEBUG);
    }

    @AfterClass
    public void baseTearDown() {

    }

}
