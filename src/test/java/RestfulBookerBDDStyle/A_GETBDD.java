package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;




public class A_GETBDD {

   @Test
   @Description("Get all ID in BDD style !!")
    public static void GetBdd() {
        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .when().log().all().get()
                .then().log().all().statusCode(200);
    }
}
