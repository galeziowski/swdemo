package com.example.demo.steps;

import com.example.demo.cucumber.CucumberContextHolder;
import com.example.demo.owner.model.OwnerProfile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerSteps {

    OwnerProfile.OwnerProfileBuilder ownerProfileBuilder = OwnerProfile.builder();

    @Autowired
    private CucumberContextHolder cucumberContext;

    private static final String OWNER_ENDPOINT = "/api/owner";

    @When("I read the OwnerProfile with id {string}")
    public void i_read_the_owner_with_id(String id) {
        String path = OWNER_ENDPOINT + "/" + id;
        cucumberContext.setResponse(when().get(path));
    }

    @Then("Owner has name set to {string}")
    public void ownerName(String name){
        OwnerProfile profile = cucumberContext.getResponse().getBody().as(OwnerProfile.class);
        assertEquals(name, profile.getName());
    }

    @Then("Owner has permissions set to {string}")
    public void ownerPermissions(String name){
        OwnerProfile profile = cucumberContext.getResponse().getBody().as(OwnerProfile.class);
        assertEquals(name, profile.getPermissions());
    }
}
