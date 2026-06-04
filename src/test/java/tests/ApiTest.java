package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.requestSpecification = RestAssured.given()
                .header("x-api-key", "free_user_3EfVJItnfn1k00NzfAP7yRjQ2yT");
    }

    @Test
    public void getUserTest() {
        Response response = RestAssured
                .given()
                .when()
                .get("/api/users/2")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Response: " + response.asString());
    }

    @Test
    public void createUserTest() {
        String requestBody = "{ \"name\": \"Janani\", \"job\": \"QA Engineer\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("Created User: " + response.asString());
    }

    @Test
    public void deleteUserTest() {
        Response response = RestAssured
                .given()
                .when()
                .delete("/api/users/2")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}