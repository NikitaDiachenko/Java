package CRUDUsers;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUser extends CreateUser {
    @Test(dataProvider = "DataForCreateUser")
    public void updateUser (String name, String gender, String email, String status) {
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
                .put(getBaseURI() + "/users/" + getUserId())
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
                .body("status", equalTo(status)).toString();

    }
    @Test (priority = 1)
    void updateNonExistUser() {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", "xxx");
        jsonBody.put("gender", "xxx");
        jsonBody.put("email", "xxx");
        jsonBody.put("status", "xxx");
        given()
                .body(jsonBody.toJSONString())
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .put(getBaseURI() + "/users/162346")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("message", equalTo("Resource not found"));
    }
    @Test (priority = 2)
    void updateUserInvalidFields() {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", "xxx");
        jsonBody.put("gender", "xxx");
        jsonBody.put("email", "xxx");
        jsonBody.put("status", "xxx");
        given()
                .body(jsonBody.toJSONString())
                .headers(
                        "Authorization",
                        "Bearer " + getBearerToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().log().all()
                .put(getBaseURI() + "/users/" + getUserId())
                .then()
                .log().all()
                .assertThat()
                .statusCode(422)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("[2].field", equalTo("email"))
                .body("[2].message", equalTo("is invalid"))
                .body("[0].field", equalTo("gender"))
                .body("[0].message", equalTo("can't be blank, can be male or female"))
                .body("[1].field", equalTo("status"));
    }
}
