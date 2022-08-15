package CRUDUsers;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetUser extends CreateUser {
    @Test
    void getUser () throws InterruptedException {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .get(getBaseURI()+"/users/" + getUserId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("id", equalTo(getUserId()))
                .body("name",equalTo(getUserName()))
                .body("email", equalTo(getUserEmail()))
                .body("gender", equalTo(getUserGender()))
                .body("status", equalTo(getUserStatus()));


                deleteUser(getUserId());

    }
    @Test (priority = 1)
    void getNonExistUser(){
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .get(getBaseURI()+"/users/" + getUserId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("message", equalTo("Resource not found"));
    }




}
