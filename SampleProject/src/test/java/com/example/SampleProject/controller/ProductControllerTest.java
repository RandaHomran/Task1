package com.example.SampleProject.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class ProductControllerTest {

    final String baseURI = "http://localhost:8080/Gradle___com_example___SampleProject_1_0_SNAPSHOT_war";

    @Before
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = baseURI;
    }

    @Test
    public void testAddProduct() throws Exception {

        JSONObject request = new JSONObject();
        request.put("id", 1);
        request.put("name", "product1");
        request.put("price", 4000);

        given().
                urlEncodingEnabled(false).
                body(request.toJSONString()).
                when().
                post("/rest/products").
                then().statusCode(200);
    }

    @Test
    public void getProductSuccess() {
        given().
            urlEncodingEnabled(false).
            pathParam("id",1).
        when().
            get("/rest/products/{id}").
        then().
            assertThat().
            statusCode(200).
            body("name", equalTo("product1")).
            body("price", equalTo(4000));
    }

    @Test
    public void getProductFail() {
        given().
            urlEncodingEnabled(false).
        when().
            get("/rest/products/4").
        then().
            statusCode(404);
    }
}