package CRUDUsers;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import static io.restassured.RestAssured.given;

public class DataForRequests {
    private final String bearerToken = "ea7fceda547b612f7b44f532fc73e72b9cf8f2d0c775cd3be3421f7376b53995";
    private final String baseURI = "https://gorest.co.in/public/v2";
    @DataProvider(name = "DataForCreateUser")
    public Object[][] dataForCreateUser() {
        Faker faker = new Faker();
    return new Object[][] {
            {faker.name().firstName(), faker.options().option("male", "female"), faker.name().firstName() + "@gmail",
                    faker.options().option("active", "inactive")}
    };
    }
    public JsonPath parseResponse(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
    public String getBaseURI() {
        return baseURI;
    }

    public String getBearerToken() {
        return bearerToken;
    }
    void deleteUser(int userId) {
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
