package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ProfileResourceTest {

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testProfilePage() {

        given()
                .when().get("/profile/1")
                .then()
                .statusCode(200)
                .body(containsString("User Profile"))
                .body(containsString("alice"))
                .body(containsString("Manager"))
                .body(containsString("55000.0 €"))
                .body(containsString("hello <span class=\"text-white font-medium capitalize\">alice</span>"));
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testEditProfilePage() {

        given()
                .when().get("/profile/edit/1")
                .then()
                .statusCode(200)
                .body(containsString("Edit Profile"))
                .body(containsString("alice"));
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testUpdateProfile() {

        String location = given()
                .formParam("name", "Alice Updated")
                .formParam("email", "alice.updated@example.com")
                .formParam("address", "Updated Wonderland")
                .formParam("phone", "999-999-999")
                .redirects().follow(false)
                .when().post("/profile/update/1")
                .then()
                .statusCode(303)
                .extract().header("Location");

        given()
                .when().get(location)
                .then()
                .statusCode(200)
                .body(containsString("Alice Updated"))
                .body(containsString("admin"))
                .body(containsString("alice.updated@example.com"));
    }

    @Test
    @TestSecurity(user = "alice", roles = "admin")
    public void testWrongProfilePage() {

        given()
                .when().get("/profile/2")
                .then()
                .statusCode(200)
                .body(containsString("User Profile"))
                .body(containsString("bob"))
                .body(containsString("hello <span class=\"text-white font-medium capitalize\">alice</span>"));
    }
}
