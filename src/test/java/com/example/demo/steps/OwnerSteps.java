package com.example.demo.steps;

import com.example.demo.cucumber.CucumberContextHolder;
import com.example.demo.owner.entity.Owner;
import com.example.demo.owner.model.OwnerProfile;
import com.example.demo.owner.repository.OwnerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OwnerSteps {

    OwnerProfile.OwnerProfileBuilder ownerProfileBuilder = OwnerProfile.builder();

    @Autowired
    private CucumberContextHolder cucumberContext;

    @Autowired
    private OwnerRepository ownerRepository;

    @Given("I setup admin user")
    public void iExecuteBeforeStep() {
        ownerRepository.save(new Owner(UUID.randomUUID(), "Łukasz", "ADMIN"));
    }

    private static final String OWNER_ENDPOINT = "/api/owner";

    @When("I have a user with name {string} and role {string}")
    public void saveUser(String name, String role) {
        cucumberContext.getOwnersList().put(name, ownerRepository.save(new Owner(UUID.randomUUID(), name, role)).getId());
    }

    @When("Get owners")
    public void getOwners() {
        cucumberContext.setResponse(when().get(OWNER_ENDPOINT));
    }

    @Then("Owners list has owner with name {string}")
    public void ownerListHasOwner(String name) {
        List<OwnerProfile> owners = Arrays.stream(cucumberContext.getResponse().getBody().as(OwnerProfile[].class)).toList();
        assertTrue(owners.stream().anyMatch(ownerProfile -> ownerProfile.getName().equals(name)));
    }

    @When("I read the OwnerProfile with id of user {string}")
    public void i_read_the_owner_with_id(String id) {
        String path = OWNER_ENDPOINT + "/" + cucumberContext.getOwnersList().get(id);
        cucumberContext.setResponse(when().get(path));
    }

    @Then("Owner has name set to {string}")
    public void ownerName(String name) {
        OwnerProfile profile = cucumberContext.getResponse().getBody().as(OwnerProfile.class);
        assertEquals(name, profile.getName());
    }

    @Then("Owner has permissions set to {string}")
    public void ownerPermissions(String name) {
        OwnerProfile profile = cucumberContext.getResponse().getBody().as(OwnerProfile.class);
        assertEquals(name, profile.getPermissions());
    }
}
