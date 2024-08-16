package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class C_PutBdd {
@Test
@Description("Put using BDD style !")
    public static void putbdd(){

        String BaseURL="https://restful-booker.herokuapp.com";
        String Basepath="/booking/814";
        String Token="c888141603eb625";
        String payload="{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RestAssured.given()
                .baseUri(BaseURL)
                .basePath(Basepath)
                .body(payload)
                .contentType(ContentType.JSON)
                .cookie("token",Token)

                .when().log().all().put()

                .then().log().all().body("firstname", Matchers.equalTo("James"));
    }
}
