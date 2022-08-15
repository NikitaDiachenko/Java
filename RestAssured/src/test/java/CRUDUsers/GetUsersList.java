package CRUDUsers;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUsersList extends CreateUser {
    @Test
    void getUsersList () throws InterruptedException {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .get(getBaseURI()+"/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("id", hasItem(getUserId()))
                .body("name", hasItem(getUserName()))
                .body("email", hasItem(getUserEmail()));



        deleteUser(getUserId());

    }
}
