import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LogIn {
    //3- LOGIN - SUCCESSFUL (Add assert for the response with status code - 200)
    @Test
    public void successful() {
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject request = new JSONObject(map);
        request.put("email", "eve.holt@reqres.in");
        request.put("password", "cityslicka");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/login").
                then().statusCode(200).
                log().all();
    }
    //4- LOGIN - UNSUCCESSFUL (Add assert for error message and status code - 400)
    @Test
    public void unSuccessful() {
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject request = new JSONObject(map);
        request.put("email", "peter@klaven");
        request.put("password", "");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/login").
                then().statusCode(400).
                log().all();
    }
}
