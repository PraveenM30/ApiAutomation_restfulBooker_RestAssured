package RestfulBookerBDDStyle;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static RestfulBookerBDDStyle.B_PostNonBDD.NonBddPost;

public class D_DeleteNonBdd extends A_tokenNonBdd {
    @Test
    @Description("Delete using NonBDD style !")

    public static void DeleteNonbdd() {
        A_tokenNonBdd t=new A_tokenNonBdd();

        String boookingId=NonBddPost();

        String BaseURL = "https://restful-booker.herokuapp.com";
        String Basepath = "/booking/"+boookingId;
        String Token = t.tokenNONBDD();


        RequestSpecification rs = RestAssured.given();
        rs.baseUri(BaseURL);
        rs.basePath(Basepath);
        rs.cookie("token", Token);

        Response res = rs.when().log().all().delete();
        System.out.println(res);

       ValidatableResponse val = res.then().log().all();
        val.statusCode(201);
        val.body("pramod", Matchers.equalTo("firstname"));

    }
}
