package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class HomeTest {

    @Test
    public void testHomePage() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("Portail RH NeuronAddict"))
                .body(containsString("Confidentialité"));
    }
}
