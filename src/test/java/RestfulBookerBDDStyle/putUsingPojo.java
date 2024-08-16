package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class putUsingPojo {
    @Test
    @Description("Put using BDD style !")
    public static void putbdd(){

        String BaseURL="https://restful-booker.herokuapp.com";
        String Basepath="/booking/814";
        String Token="af257b5386abe0d";

        pojo pojo=new pojo("praveen");

        String payload=pojo.getFirstname();

        RestAssured.given()
                .baseUri(BaseURL)
                .basePath(Basepath)
                .body(payload)
                .contentType(ContentType.JSON)
                .cookie("token",Token)

                .when().log().all().put()

                .then().log().all().body("firstname", Matchers.equalTo("praveen"));
    }
}

