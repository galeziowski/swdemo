package com.example.demo.steps;

import com.example.demo.cucumber.CucumberContextHolder;
import com.example.demo.schedule.Slot;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScheduleSteps {

    @Autowired
    private CucumberContextHolder cucumberContext;

    private static final String API_PATH = "/api/schedule";

    @When("I get schedule slots")
    public void getScheduleSlots(){
        cucumberContext.setResponse(when().get(API_PATH));
    }

    @Then("Schedule list contains entry for year {int}, month {int}, day {int}, hour {int}, minute {int} and vetId {string}")
    public void checkScheduleSlot(Integer year, Integer month, Integer day, Integer hour, Integer minute, String vetId){
        List<Slot> slots = Arrays.stream(cucumberContext.getResponse().as(Slot[].class)).toList();
        assertTrue(slots.contains(new Slot(LocalDateTime.of(year, month, day, hour, minute), vetId)));
    }
}
