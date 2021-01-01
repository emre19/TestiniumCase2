package getRequest;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    private static Logger log = Logger.getLogger(UserApiTest.class);

    @BeforeClass
    public static void getData() {
        log.info("Test started");
    }

    //Get all users
    @Test(priority = 1)
    public void getAllUsers(){

        given().
                get("https://reqres.in/api/users").
                then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                log().all();
    }

    //Get user ID=2
    @Test(priority = 2)
    public void getUser(){

        given().
                get("https://reqres.in/api/users/2").
                then().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                log().all();

    }

    //Create user
    @Test(priority = 3)
    public void createUser(){

        JSONObject request = new JSONObject();

        request.put("name", "Taha Emre DİKER");
        request.put("job", "Software Test Engineer");

        System.out.println(request);

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                statusLine("HTTP/1.1 201 Created").
                log().all();

    }

    //Delete user
    @Test(priority = 4)
    public void deleteUser(){

        when().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).
                statusLine("HTTP/1.1 204 No Content").
                log().all();
    }

    @AfterClass
    public void after() {
        log.info("Test Finished");
    }

}

