import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static org.hamcrest.CoreMatchers.containsString;

public class ApiBaseTest {

    JsonObject requestCredentials = new JsonObject();

    RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri("http://207.154.242.0")
            .setPort(8888)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.ANY)
            .build();

    ResponseSpecification responseSuccess = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(containsString("success"))
            .build();

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Test <" + testInfo.getDisplayName() + "> starts running");
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Test <" + testInfo.getDisplayName() + "> finished");
        System.out.println("---------------------------------------------------------------------------------");
    }

}
