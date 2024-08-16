package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class A_GETNonBDD {

    @Test
    @Description("Get All the ID in NON BDD style!!")
    public static void GetNonBDD() {

        RequestSpecification rs = RestAssured.given();

        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        Response res = rs.when().log().all().get();
        String responsestring = res.toString();
        System.out.println(responsestring);

        ValidatableResponse val = res.then();
        val.statusCode(200);

    }
}
