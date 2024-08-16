package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class A_tokenBDD {
    @Test
    @Description("Token is generated in BDD style!! ")
    public void tokenBDD() {

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().log().all().post()
                .then().log().all().statusCode(200);
    }
}
