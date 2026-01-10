package org.neuronaddict.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ProfileResourceTest {

    @Test
    public void testProfilePage() {

        given()
                .when().get("/profile/1")
                .then()
                .statusCode(200)
                .body(containsString("User Profile"))
                .body(containsString("Alice"));
    }

    @Test
    public void testEditProfilePage() {

        given()
                .when().get("/profile/edit/1")
                .then()
                .statusCode(200)
                .body(containsString("Edit Profile"))
                .body(containsString("Alice"));
    }

    @Test
    public void testUpdateProfile() {

        String location = given()
                .formParam("name", "Alice Updated")
                .formParam("email", "alice.updated@example.com")
                .formParam("address", "Updated Wonderland")
                .formParam("phone", "999-999-999")
                .formParam("role", "Admin")
                .redirects().follow(false)
                .when().post("/profile/update/1")
                .then()
                .statusCode(303)
                .extract().header("Location");
        ; // Redirect

        given()
                .when().get(location)
                .then()
                .statusCode(200)
                .body(containsString("Alice Updated"))
                .body(containsString("Admin"));
    }
}
