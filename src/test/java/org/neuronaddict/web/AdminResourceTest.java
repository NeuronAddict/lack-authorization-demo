package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class AdminResourceTest {

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testAdminPageAsAdmin() {
        given()
                .when().get("/admin")
                .then()
                .statusCode(200)
                .body(containsString("Évaluations"))
                .body(containsString("alice"))
                .body(containsString("bob"))
                .body(containsString("eve"))
                .body(containsString("Alice est une employée exceptionnelle"))
                .body(containsString("Bob a fait beaucoup de progrès"))
                .body(containsString("Eve est très rigoureuse"));
    }

    @Test
    public void testAdminPageAsUser() {
        given()
                .redirects().follow(false)
                .when().get("/admin")
                .then()
                .statusCode(302);
    }

    @Test
    @TestSecurity(user = "bob", roles = "user")
    public void testAdminLinkHiddenForUser() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(not(containsString("/admin")));
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testAdminLinkVisibleForAdmin() {
        given()
                .when().get("/")
                .then()
                .statusCode(200)
                .body(containsString("/admin"));
    }
}
