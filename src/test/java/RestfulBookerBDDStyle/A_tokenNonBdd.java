package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class A_tokenNonBdd {
    @Test
    @BeforeTest
    @Description("Token is generated in BDD style!! ")
    public String tokenNONBDD() {

        RequestSpecification res = RestAssured.given();
        res.baseUri("https://restful-booker.herokuapp.com/auth");
        res.contentType(ContentType.JSON);
        res.body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}");


       Response r= res.post();

        ValidatableResponse v=r.then();
        v.statusCode(200);

        String token=r.then().log().all().extract().path("token");
        Assert.assertNotNull(token);

        return token;
    }
}
