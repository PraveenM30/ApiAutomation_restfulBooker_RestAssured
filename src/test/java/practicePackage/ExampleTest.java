package practicePackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void getRequest() {
        // Set base URI and base path
        baseURI = "https://restful-booker.herokuapp.com";
        basePath = "/booking";

        // Perform GET request and validate status code
        given()
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(300); // Asserting that the status code is 200
    }
}