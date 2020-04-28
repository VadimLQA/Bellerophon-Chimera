package com.norwegian.stepimplementation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author vloparevich
 **/
public class BDDImplmentation {

    @Given("^Lands on the main page$")
    public void lands_on_the_main_page() {
        System.out.println("User lands on the main page");
    }

    @When("^User navigates to explore$")
    public void user_navigates_to_explore() {
        System.out.println("User navigated to the explore port");
    }

    @Then("^User is able to find the port$")
    public void user_is_able_to_find_the_port() {
        System.out.println("User can see the port in the middle of the screen");
    }
}
