package CRUDUsers;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUser extends DataForRequests {
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

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Test
    public void createUserWithoutValues() {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", "");
        jsonBody.put("gender", "");
        jsonBody.put("email", "");
        jsonBody.put("status", "");
        given()
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
                .statusCode(422)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("[0].field", equalTo("email"))
                .body("[0].message", equalTo("can't be blank"))
                .body("[1].field", equalTo("name"))
                .body("[2].field", equalTo("gender"))
                .body("[2].message", equalTo("can't be blank, can be male or female"))
                .body("[3].field", equalTo("status"));


    }

    @Test
    public void createUserWithoutAuthorization() {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name", "");
        jsonBody.put("gender", "");
        jsonBody.put("email", "");
        jsonBody.put("status", "");
        given()
                .body(jsonBody.toJSONString())
                .headers(
                        "Authorization",
                        "Bearer " + "",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .post(getBaseURI() + "/users")
                .then()
                .assertThat()
                .statusCode(401)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Server", "cloudflare")
                .body("message", equalTo("Authentication failed"));

    }

    @Test (priority = 1)
    public void createUserWithInvalidValues() {
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
                .when()
                .post(getBaseURI() + "/users")
                .then().log().all()
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
