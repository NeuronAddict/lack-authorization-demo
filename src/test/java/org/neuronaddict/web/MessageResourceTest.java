package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MessageResourceTest {

    @Test
    public void testRedirectLogin() {

        given()
                .redirects().follow(false)
                .when().get("/messages/1")
                .then()
                .statusCode(302);
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testMessagesPage() {
        given()
                .when().get("/messages/1")
                .then()
                .statusCode(200)
                .body(containsString("Envoyez un message, vous recevrez une réponse par mail de\n" +
                        "                notre service RH."))
                .body(containsString("Quand est ce que mon augmentation sera présente sur ma fiche de paie ?"));
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
