import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@DisplayName("Registration API Tests")
public class RegisterAPITests extends ApiBaseTest{

    JsonObject addressList = new JsonObject();
    Helper rnd = new Helper();

    String emailAlr = "DavidPBowe@jourrapide.com";
    String birthDateVal = "1954-01-12T00:00:00.000Z";
    String descriptionVal = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
    String countryVal = "US";
    String cityVal = "Houston";
    String stateVal = "Hous";
    String zipVal = "PA 16412";
    String streetVal = "2698 Spadafore Drive";

    @Tag("200")
    @Tag("All")
    @DisplayName("api_1_01 > Check request with new register")
    @Test
    public void postNewRegister(){
        requestCredentials.addProperty("email", rnd.getValidEmail(5));
        requestCredentials.addProperty("pwd", rnd.getValidPass(12));
        requestCredentials.addProperty("phone", rnd.getValidPhone(10));
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);
        requestCredentials.add("address", addressList);
            addressList.addProperty("country", countryVal);
            addressList.addProperty("city", cityVal);
            addressList.addProperty("state", stateVal);
            addressList.addProperty("zip", zipVal);
            addressList.addProperty("street", streetVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("none"));

        }

    @Tag("400")
    @Tag("Email")
    @DisplayName("api_2_02 > Check request with already existing email")
    @Test
    public void postEmailExist(){

        requestCredentials.addProperty("email", emailAlr);
        requestCredentials.addProperty("phone", rnd.getValidPhone(8));
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field email already exists"));
    }

    @Tag("200")
    @Tag("All")
    @DisplayName("api_1_02 > Check request with obligatory fields only")
    @Test
    public void postObligatoryFields(){

        requestCredentials.addProperty("email", rnd.getValidEmail(7));
        requestCredentials.addProperty("pwd", rnd.getValidPass(13));
        requestCredentials.addProperty("phone", rnd.getValidPhone(11));
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("none"));
    }


    @Tag("400")
    @Tag("All")
    @DisplayName("api_1_03 > Check request without obligatory fields")
    @Test
    public void postWithoutObligatory(){

        requestCredentials.add("address", addressList);
        addressList.addProperty("country", countryVal);
        addressList.addProperty("city", cityVal);
        addressList.addProperty("state", stateVal);
        addressList.addProperty("zip", zipVal);
        addressList.addProperty("street", streetVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field email missed"));

    }


    @Tag("400")
    @Tag("All")
    @DisplayName("api_1_04 > Check request with empty value")
    @Test
    public void postWithEmptyVal(){

        requestCredentials.addProperty("email", "");
        requestCredentials.addProperty("pwd", "");
        requestCredentials.addProperty("phone", "");
        requestCredentials.addProperty("birthDate", "");
        requestCredentials.addProperty("description", "");
        requestCredentials.add("address", addressList);
        addressList.addProperty("country", "");
        addressList.addProperty("city", "");
        addressList.addProperty("state", "");
        addressList.addProperty("zip", "");
        addressList.addProperty("street", "");


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field email missed"));
    }

    @Tag("400")
    @Tag("All")
    @DisplayName("api_2_03 > Check request with blank email")
    @Test
    public void postWithBlankEmail(){

        requestCredentials.addProperty("email", "");
        requestCredentials.addProperty("pwd", rnd.getValidPass(10));
        requestCredentials.addProperty("phone", rnd.getValidPhone(11));
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field email missed"));
    }

    @Tag("400")
    @Tag("All")
    @DisplayName("api_2_18 > Check request with email no @ or domain")
    @Test
    public void postWithoutDomainEmail(){

        requestCredentials.addProperty("email", "johndoe");
        requestCredentials.addProperty("pwd", rnd.getValidPass(11));
        requestCredentials.addProperty("phone", rnd.getValidPhone(12));
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field email bad format"));
    }

    @Tag("200")
    @Tag("phone")
    @DisplayName("api_4_19 > Check request with blank phone")
    @Test
    //@Disabled("DEFECT: Phone should not be obligate field")
    public void postPhoneBlank(){

        requestCredentials.addProperty("email", rnd.getValidEmail(6));
        requestCredentials.addProperty("pwd", rnd.getValidPass(6));
        requestCredentials.addProperty("phone", "");
        requestCredentials.addProperty("birthDate", birthDateVal);
        requestCredentials.addProperty("description", descriptionVal);

        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(200)
                .and()
                .body(containsString("none"));
    }

    @Tag("400")
    @Tag("birthDate")
    @DisplayName("api_5_02 > Check request without birthDate")
    @Test
    public void postBirthDateMissed(){

        requestCredentials.addProperty("email", rnd.getValidEmail(8));
        requestCredentials.addProperty("phone", rnd.getValidPhone(11));


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field birthDate missed"));
    }

    @Tag("400")
    @Tag("birthDate")
    @DisplayName("api_5_05 > Check request with less 21 year old")
    @Test
    public void postBirthDateLess(){

        requestCredentials.addProperty("email", rnd.getValidEmail(4));
        requestCredentials.addProperty("phone", rnd.getValidPhone(9));
        requestCredentials.addProperty("birthDate", "2019-01-01T00:00:00.000Z");
        requestCredentials.addProperty("description", descriptionVal);


        given().spec(request)
                .contentType("application/json")
                .body(requestCredentials.toString())
                .when().post(EndPoints.register)
                .then()
                .statusCode(400)
                .and()
                .body(containsString("Field birthDate bad format"));
    }

}
