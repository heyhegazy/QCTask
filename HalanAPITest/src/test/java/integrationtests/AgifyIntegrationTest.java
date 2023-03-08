package integrationtests;

import io.restassured.RestAssured;
import net.joshka.junit.json.params.JsonFileSource;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import javax.json.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AgifyIntegrationTest {
    private static final String AGIFY_BASE_URL = "https://api.agify.io";

    @BeforeAll
    public static void before() {
        RestAssured.baseURI = AGIFY_BASE_URL;
    }

    @DisplayName("Should check API Status Code")
    @Test
    public void shouldReturnStatusCodeOK200() {
        given().get("/?name=meelad")
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("Should pass a json object to test if the API response is equal to json file data")
    @ParameterizedTest
    @JsonFileSource(resources = {"/testdata.json"})
    public void shouldCheckNameAgeCountUsingJSONFile(JsonObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int count = jsonObject.getInt("count");
        given().get("/?name=" + name)
                .then()
                .assertThat()
                .body("name", equalTo(name))
                .body("age", equalTo(age))
                .body("count", equalTo(count));
    }
}
