package Rest_Assured;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class UsingMap extends payload {


    //    {
//        "firstname" : "Jim",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//        "additionalneeds" : "Breakfast"
//    }
    @Test
    public void TestPost() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Integer totalPrice = faker.random().nextInt(1000);
        Boolean depositpaid = faker.random().nextBoolean();
        String checkin = "2018-01-01";
        String checkout = "2019-01-05";
        String breakfast = "Breakfast";


        RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON).log().all();
        rs.body(PostPayloadForIDCreation(firstName, lastName, totalPrice, depositpaid, checkin, breakfast, checkout));

        Response response = rs.when().log().all().post();
        String responseString = response.asString();
        System.out.println(responseString);

        ValidatableResponse val = response.then();
        val.statusCode(200);
    }
}
