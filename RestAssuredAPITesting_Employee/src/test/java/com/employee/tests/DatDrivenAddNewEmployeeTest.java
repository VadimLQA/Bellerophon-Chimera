package com.employee.tests;

import com.employee.basetest.BaseTest;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author vloparevich
 **/
public class DatDrivenAddNewEmployeeTest extends BaseTest {


    @BeforeClass()
    public void beforeClassSetUp() throws InterruptedException {
        logger.info("**********Started DDD ***********");

    }

    @Test(dataProvider = "employees_data", dataProviderClass = testdata.DataProviders.class, priority = 1)
    public void createMultipleEmployess(String name, String salary, String empAge) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("salary", salary);
        requestParams.put("age", empAge);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/create");
        asyncWait(response);

        System.out.println("response body with dataprovider: \n" + response.getBody().asString());

        String responseBody = response.getBody().asString();
        assertTrue(responseBody.indexOf(name) > 0);
        assertTrue(responseBody.indexOf(salary) > 0);
        assertTrue(responseBody.indexOf(empAge) > 0);
        assertTrue(response.getStatusCode() == 200);


        int id = response.jsonPath().get("data.id");
        httpRequest.request(Method.DELETE, "/delete/" + id);
        asyncWait(response);

        String status = response.jsonPath().get("status");
        assertTrue(status.equals("success"));
    }
}
