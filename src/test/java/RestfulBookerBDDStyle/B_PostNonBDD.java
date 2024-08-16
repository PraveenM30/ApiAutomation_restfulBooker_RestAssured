package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class B_PostNonBDD {
    public static long bookingidd;

    @Test
    @Description("Post Non BDD style!")
    public static String NonBddPost() {
        String baseUrl = "https://restful-booker.herokuapp.com";
        String BasePath = "/booking";
        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification r = RestAssured.given();
        r.baseUri(baseUrl);
        r.basePath(BasePath);
        r.contentType(ContentType.JSON);
        r.body(payload);

        Response response = r.when().log().all().post();
        String responseString = response.toString();
        System.out.println(responseString);

        ValidatableResponse validateresponse = response.then();
        validateresponse.statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        // Extract a specific value using JSON Path
        String bookingid = jsonPath.getString("bookingid");
        return bookingid;
    }

}
