import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class Register {
    //1- REGISTER - SUCCESSFUL (Add assert for the response with status code - 200)
    @Test
    public void successful() {
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject request = new JSONObject(map);
        request.put("email", "eve.holt@reqres.in");
        request.put("password", "pistol");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/register").
                then().statusCode(200).
                log().all();
    }
    //2- REGISTER - UNSUCCESSFUL (Add assert for error message and status code - 400)
    @Test
    public void unSuccessful() {
        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject request = new JSONObject(map);
        request.put("email", "sydney@fife");
        request.put("password", "");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().post("/register").
                then().statusCode(400).
                log().all();
    }
}
