package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class EvalResourceTest {

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testEvalPage() {

        given()
                .when().get("/eval/1")
                .then()
                .statusCode(200)
                .body(containsString("Evaluation Annuelle"))
                .body(containsString("alice"))
                .body(containsString("Alice est une employée exceptionnelle"))
                .body(containsString("Ces informations sont strictement confidentielles"));
    }
}
