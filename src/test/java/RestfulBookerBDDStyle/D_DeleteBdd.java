package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class D_DeleteBdd {
    @Test
    @Description("Delete using NonBDD style !")

    public static void Deletebdd() {

        String BaseURL = "https://restful-booker.herokuapp.com";
        String Basepath = "/booking/1407";
        String Token = "2906da8a1326e5f";


        RestAssured.given()
        .baseUri(BaseURL)
                .basePath(Basepath)
        .cookie("token", Token).

       when().log().all().delete()


       .then().log().all().statusCode(201);

    }
}
