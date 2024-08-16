package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static RestfulBookerBDDStyle.B_PostNonBDD.NonBddPost;

public class C_PutNonBdd extends A_tokenNonBdd {
    @Test
    @Description("Delete using NonBDD style !")

    public static void putNonbdd() {
        A_tokenNonBdd t=new A_tokenNonBdd();

       String bookingID= NonBddPost();

        String BaseURL = "https://restful-booker.herokuapp.com";
        String Basepath = "/booking/"+ bookingID;
        String Token =t.tokenNONBDD();
        String payload = "{\n" +
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

        RequestSpecification rs = RestAssured.given();
        rs.baseUri(BaseURL);
        rs.basePath(Basepath);
        rs.contentType(ContentType.JSON);
        rs.body(payload);
        rs.cookie("token", Token);

        Response res = rs.when().log().all().put();
        System.out.println(res);

       ValidatableResponse val = res.then().log().all();

        val.body("firstname", Matchers.equalTo("James"));
        val.statusCode(200);
    }
}
