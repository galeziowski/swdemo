package com.example.demo.cucumber;

import com.example.demo.util.JWTUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.nimbusds.jose.JOSEException;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {

    @LocalServerPort
    private int port;

    static WireMockServer wireMockServer;

    @PostConstruct
    public void initRestAssured() {
        RestAssured.port = port;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.jwk-set-uri", ()->wireMockServer.baseUrl());
    }

    @BeforeAll
    public static void before_or_after_all() {

        wireMockServer = new WireMockServer(0);
        wireMockServer.start();

        var jwkResponse = String.format("{\"keys\": [%s]}", JWTUtils.getPublicRsaKey().toJSONString());

        wireMockServer.stubFor(get("/")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jwkResponse)));
    }


}
