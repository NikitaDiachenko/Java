package E2EUsers;

import CRUDUsers.DataForRequests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;

public class E2EUsers extends DataForRequests {
    private Integer userId;
    private String userName;
    private String userGender;
    private String userStatus;
    private String userEmail;

    @Test(dataProvider = "DataForCreateUser")
    public void createUser(String name, String gender, String email, String status) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", name);
        jsonBody.put("gender", gender);
        jsonBody.put("email", email);
        jsonBody.put("status", status);
        String response = given()
                .body(jsonBody.toJSONString())
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .post(getBaseURI() + "/users")
                .then()
                .assertThat()
                .statusCode(201)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("name", equalTo(name))
                .body("id", notNullValue())
                .body("email", equalTo(email))
                .body("gender", equalTo(gender))
                .body("status", equalTo(status))
                .extract().response().asString();
        JsonPath parsedResponse = parseResponse(response);
        this.userId = parsedResponse.getInt("id");
        this.userGender = parsedResponse.getString("gender");
        this.userName = parsedResponse.getString("name");
        this.userStatus = parsedResponse.getString("status");
        this.userEmail = parsedResponse.getString("email");

    }

    @Test
    void getUser() {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .get(getBaseURI() + "/users/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("id", equalTo(userId))
                .body("name", equalTo(userName))
                .body("email", equalTo(userEmail))
                .body("gender", equalTo(userGender))
                .body("status", equalTo(userStatus));
    }

    @Test(dataProvider = "DataForCreateUser")
    public void updateUser(String name, String gender, String email, String status) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", name);
        jsonBody.put("gender", gender);
        jsonBody.put("email", email);
        jsonBody.put("status", status);
        String response = given()
                .body(jsonBody.toJSONString())
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .put(getBaseURI() + "/users/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("name", equalTo(name))
                .body("id", notNullValue())
                .body("email", equalTo(email))
                .body("gender", equalTo(gender))
                .body("status", equalTo(status))
                .extract().response().asString();
                JsonPath parsedResponse = parseResponse(response);
                this.userId = parsedResponse.getInt("id");
                this.userGender = parsedResponse.getString("gender");
                this.userName = parsedResponse.getString("name");
                this.userStatus = parsedResponse.getString("status");
                this.userEmail = parsedResponse.getString("email");

    }

    @Test (priority = 1)
    void getUsersList()  {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .get(getBaseURI() + "/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("id", hasItem(userId))
                .body("name", hasItem(userName))
                .body("email", hasItem(userEmail));

    }
    @Test (priority = 2)
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
                .delete(getBaseURI() + "/users/" + userId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .header("Server", "cloudflare");

    }
}
