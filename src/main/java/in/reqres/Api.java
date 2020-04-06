package in.reqres;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.reqres.pojo.User;
import in.reqres.pojo.Users;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;

import static in.reqres.DataMap.mapObject;
import static in.reqres.Tests.JOB;
import static in.reqres.Tests.NAME;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class Api {

    public void setValueToJson(String var, String val) {
        mapObject().put(var, val);
    }

    public Response postRequest(String baseUrl, String absolute) {
        RestAssured.baseURI = baseUrl;
        return given().
                contentType(ContentType.JSON).
                body(new JSONObject(mapObject()).toString()).
                when().
                post(absolute).
                then().extract().response();
    }

    public Response getRequest(String baseUrl, String absolute) {
        RestAssured.baseURI = baseUrl;
        return given().
                when().
                get(absolute).
                then().statusCode(200).extract().response();
    }

    public void checkResponse(Response response) {
        /** вариант с маппингом*/
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(response.getBody().asString(), User.class);
            assertEquals(user.getJob(), mapObject().get(JOB));
            assertEquals(user.getName(), mapObject().get(NAME));
            System.out.println(user.getJob());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        /** вариант без маппинга, чтоб не плодить сущности, когда json простой*/
        for (String key : mapObject().keySet()) {
            assertEquals(response.jsonPath().get(key), mapObject().get(key));
        }


    }

    public void usersAssertNotNull(String baseUrl, String absoluteUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Users users = objectMapper.readValue(getRequest(baseUrl, absoluteUrl).getBody().asString(), Users.class);
            assertNotNull(users.getPage());
            assertNotNull(users.getPerPage());
            assertNotNull(users.getTotalPages());
            assertNotNull(users.getTotal());

            for (int i = 0; i < users.getData().size(); i++) {
                assertNotNull(users.getData().get(i).getAvatar());
                assertNotNull(users.getData().get(i).getEmail());
                assertNotNull(users.getData().get(i).getId());
                assertNotNull(users.getData().get(i).getFirstName());
                assertNotNull(users.getData().get(i).getLastName());
            }
            assertNotNull(users.getAd().getCompany());
            assertNotNull(users.getAd().getText());
            assertNotNull(users.getAd().getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
