package CRUDUsers;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUser extends CreateUser {
    @Test
    void deleteUser() {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .delete(getBaseURI() + "/users/" + getUserId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .header("Server", "cloudflare");

    }
    @Test (priority = 1)
    void checkIfUserDeleted(){
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
    @Test (priority = 2)
    void deleteNonExistUser(){
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .delete(getBaseURI() + "/users/" + getUserId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Server", "cloudflare")
                .body("message", equalTo("Resource not found"));
    }
}
