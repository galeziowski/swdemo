package com.example.demo.steps;

import com.example.demo.cucumber.CucumberContextHolder;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.demo.util.JWTUtils.getSignedJwt;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommonSteps {


    @Autowired
    private CucumberContextHolder cucumberContext;

    static RequestSpecification getRequestSpec(CucumberContextHolder cucumberContext) {
        if (cucumberContext.isUserLoggedIn()) {
            try {
                return given().header("Authorization", format("Bearer %s", getSignedJwt(cucumberContext.getCurrentlySelectedUser())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return given();
        }
    }

    @Then("I receive a correct response")
    public void i_receive_a_correct_response() {
        System.out.println(cucumberContext.getResponse().asPrettyString());
        assertEquals(200, cucumberContext.getResponse().getStatusCode());
    }

    @Then("I receive an error")
    public void i_receive_an_error() {
        System.out.println(cucumberContext.getResponse().asPrettyString());
        assertNotEquals(200, cucumberContext.getResponse().getStatusCode());
    }

    @When("User Logs In")
    public void logIn(){
        cucumberContext.setUserLoggedIn(true);
    }

    @When("User Logs Out")
    public void logOut(){
        cucumberContext.setUserLoggedIn(false);
    }

    @After
    public void doSomethingAfter(Scenario scenario){
        logOut();
    }




}