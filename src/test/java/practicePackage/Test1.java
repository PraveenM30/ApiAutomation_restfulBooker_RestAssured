package practicePackage;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test1 {

    @Description
    @Test
    public void m2(){
        RestAssured
                .given().baseUri("https://reqres.in").basePath("/api/users").queryParam("?page=2")//URL
                .when().get()//HTTP Method
                .then().statusCode(200)//Assertion
                .log().all();//to print Response in console
    }
}
