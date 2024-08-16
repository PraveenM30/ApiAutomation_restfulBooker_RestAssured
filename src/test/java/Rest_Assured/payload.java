package Rest_Assured;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class payload {
    public static Map<String, Object> PostPayloadForIDCreation(String firstname,String lastname, Integer totalprice, Boolean depositpaid, String checkin, String checkout, String additionalneeds) {

        Map<String, Object> jsonBodyUsingMap = new LinkedHashMap<>();

        jsonBodyUsingMap.put("firstname", firstname);
        jsonBodyUsingMap.put("lastname", lastname);
        jsonBodyUsingMap.put("totalprice", totalprice);
        jsonBodyUsingMap.put("depositpaid",depositpaid) ;

        Map<String, Object> bookingdates = new LinkedHashMap<>();
        bookingdates.put("checkin",checkin );
        bookingdates.put("checkout",checkout );

        jsonBodyUsingMap.put("bookingdates", bookingdates);
        jsonBodyUsingMap.put("additionalneeds", additionalneeds);
        System.out.println(jsonBodyUsingMap);

        return jsonBodyUsingMap;
    }
}