package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
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

    @Test
    @TestSecurity(user = "bob", roles = "user")
    public void testConnectedHomePage() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("hello <span class=\"text-white font-medium capitalize\">bob</span>"))
                .body(containsString("Portail RH NeuronAddict"))
                .body(containsString("Confidentialité"));
    }
}
