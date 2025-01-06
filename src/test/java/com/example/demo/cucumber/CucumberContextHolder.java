package com.example.demo.cucumber;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
@Setter
public class CucumberContextHolder {
    private Response response;
    private Map<String, UUID> ownersList = new HashMap<>();
    private boolean userLoggedIn;
    private String currentlySelectedUser;
}
