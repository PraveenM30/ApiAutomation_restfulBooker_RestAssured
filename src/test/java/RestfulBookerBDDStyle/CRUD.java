package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


public class CRUD {

    RequestSpecification requestspecification = RestAssured.given();

    Response response;
    ValidatableResponse validateresponse;
    Integer bookingid;
    String token;

    @Test(priority = 1)
    @Description("token generation !")

    public void tokenGenerate() {
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/auth");
        requestspecification.contentType(ContentType.JSON);
        requestspecification.body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}");
        response = requestspecification.when().post();

        validateresponse = response.then().log().all();
        validateresponse.statusCode(200);

        // JsonPath js=new JsonPath(res);
        token = response.then().log().all().extract().path("token");
    }

    @Test(priority = 2)
    @Description(" Create booking !")

    public void CreateBookingID() {
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking");
        requestspecification.contentType(ContentType.JSON);
        requestspecification.body("{\n" +
                "    \"firstname\" : \"Praveen\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        response = requestspecification.when().post();

        validateresponse = response.then().log().all().statusCode(200);

        bookingid = response.then().extract().path("bookingid");
    }

    @Test(priority = 3)
    @Description(" Update booking ID !")

    public void update() {
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/" + bookingid);
        requestspecification.contentType(ContentType.JSON);
        requestspecification.cookie("token", token);
        requestspecification.body("{\n" +
                "    \"firstname\" : \"Manoj\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        response = requestspecification.when().put();
        String fullResponseToString = response.toString();

        String firstname = response.then().log().all().extract().path("firstname");
        Assert.assertEquals(firstname, "Manoj");
    }

    @Test(priority = 4)
    @Description(" Delete booking ID !")

    public void Delete() {
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/" + bookingid);

        response = requestspecification.when().delete();

        validateresponse = response.then().log().all().statusCode(201);
    }
}