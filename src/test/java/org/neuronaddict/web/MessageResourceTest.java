package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MessageResourceTest {

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testMessagesPage() {
        given()
                .when().get("/messages/1")
                .then()
                .statusCode(200)
                .body(containsString("Messages de alice"))
                .body(containsString("Premier message pour Alice"))
                .body(containsString("Deuxième message pour Alice"));
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testPostMessage() {
        given()
                .redirects().follow(false)
                .formParam("content", "Nouveau message de test")
                .when().post("/messages/1")
                .then()
                .statusCode(303);

        given()
                .when().get("/messages/1")
                .then()
                .statusCode(200)
                .body(containsString("Nouveau message de test"));
    }
}
