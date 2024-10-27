package com.example.demo.steps;

import com.example.demo.cucumber.CucumberContextHolder;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommonSteps {


    @Autowired
    private CucumberContextHolder cucumberContext;

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


}