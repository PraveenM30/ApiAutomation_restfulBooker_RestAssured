package practicePackage;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBdd {


    RequestSpecification rs = RestAssured.given();
    @Test
    public void getrequest() {

        RestAssured.baseURI="https://app.fireflink.com/appmanagement/optimize/v1/public/user/signin\n";
       // rs.basePath("/booking/2002").log().all();
        rs.when().body("{\n" +
                "\"emailId\": \"praveen.m@fireflink.com\",\n" +
                "\"password\": \"Password@123\",\n" +
                "\"lastSessionData\": \"/signin\"\n" +
                "}").post();
        rs.then().log().all().statusCode(600);
    }

    @Test
    public void InvalidGetrequest() {
        RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking-1").log().all();
        rs.when().get();
        rs.then().statusCode(404);
    }
}
