import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class AuthorizeAPITests extends ApiBaseTest{
    Helper rnd = new Helper();

    String username = "test0@qqq.com";
    String password = "asdfgh";

    @Tag("200")
    @Tag("Login")
    @DisplayName("api_8_01 > Check request with already existing username")
    @Test
    public void postLogin (){
        requestCredentials.addProperty("login", username);
        requestCredentials.addProperty("pwd", password);


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("AAABBBCCCDDDEEE=="));

    }

    @Tag("401")
    @Tag("Login")
    @DisplayName("api_9_02 > Check request with invalid password")
    @Test
    public void postLoginInvalidPass(){
        requestCredentials.addProperty("login", username);
        requestCredentials.addProperty("pwd", rnd.getValidPass(12));


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(401)
                .and()
                .body(containsString("Failed to authorize"));

    }

    @Tag("401")
    @Tag("Login")
    @DisplayName("api_8_02 > Check request with invalid username")
    @Test
    public void postLoginInvalidName(){
        requestCredentials.addProperty("login", rnd.getValidUsername(9));
        requestCredentials.addProperty("pwd", password);


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(401)
                .and()
                .body(containsString("User does not exists"));

    }

    @Tag("401")
    @Tag("Login")
    @DisplayName("api_8_03 > Check request with blank username")
    @Test
    @Disabled("DEFECT: Get 500 error instead of 401")
    public void postLoginBlankUsername(){
        requestCredentials.addProperty("login", "");
        requestCredentials.addProperty("pwd", password);


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(401)
                .and()
                .body(containsString("Failed to authorize"));

    }

    @Tag("401")
    @Tag("Login")
    @DisplayName("api_9_03 > Check request with blank password")
    @Test
    @Disabled("DEFECT: Get 500 error instead of 401")
    public void postLoginBlankPassword(){
        requestCredentials.addProperty("login", username);
        requestCredentials.addProperty("pwd", "");


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(401)
                .and()
                .body(containsString("Failed to authorize"));

    }

    @Tag("401")
    @Tag("Login")
    @DisplayName("api_8_00 > Check request with blank value")
    @Test
    @Disabled("DEFECT: Get 500 error instead of 401")
    public void postLoginBlank(){
        requestCredentials.addProperty("login", "");
        requestCredentials.addProperty("pwd", "");


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.authorize)
                .then()
                .statusCode(401)
                .and()
                .body(containsString("Failed to authorize"));

    }

}
