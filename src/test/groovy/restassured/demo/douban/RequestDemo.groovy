package restassured.demo.douban

import static org.junit.Assert.*;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

/**
 * Created by Administrator on 2017/8/5.
 */
class RequestDemo extends Douban {
    def "how to use RestAssured queryParam"() {
        expect:
        def userName = "wadexu";
        def password = "NzrmRcIfIW4=";
        given().queryParam("userName", userName)
               .queryParam("password", password)
        .expect().statusCode(200)
        .body("success", equalTo(true),
                "userInfo.userId", equalTo("wadexu"),
                        "userInfo.firstName", equalTo("Wade"),
                        "userInfo.lastName", equalTo("Xu"),
                        "error", equalTo(null))
        .when().get("/user/login");
    }

    def "how to use RestAssured pathParam"() {
        expect:
        given.when().post("/reserve/{hotelId}/{roomNumber}", "My Hotel", 23);

        given().pathParam("hotelId", "My Hotel").
                pathParam("roomNumber", 23)
        .when().post("/reserve/{hotelId}/{roomNumber}");
    }

    def "how to add request body"() {
        expect:
        final String bodyString = "{\"customerId\": \"CDICC\",\"broker\": \"test\",\"editUserId\": \"wadexu\"}";

        given()
        .contentType("application/json")
        .request().body(bodyString)
        .expect().statusCode(200)
        .body("order.orderNumber", is(Number.class),
              "order.deleteDate", is(nullValue()),
              "success", equalTo(true)).
        when().post("/order");
    }

    def "how to add header"() {
        expect:
        given().header("Content-Type", "application/json")
        given().headers("Accept", "application/json", "Content-Type", "application/json")
    }

    def "how to verify status code"() {
        expect:
        final String orderNumber = "3017";
        final String orderVersion = "1";
        final String versionType = "";
        final String editUserId = "";
        final String customerId = "";
        final String state = "";
        given().parameters("orderNumber", orderNumber,
                "orderVersion", orderVersion,
                "versionType", versionType,
                "editUserId", editUserId,
                "customerId", customerId,
                "state", state)
        .expect().statusCode(415)
        .when().post("/order/open");
    }

    def "how to add cookie"() {
        expect:
        expect().statusCode(403).
        when().get("/access");

        given().cookie("userName", "wadexu").
        expect().statusCode(200).
        when().get("/access");
    }

    def "how to authenticate"() {
        expect:
        expect().statusCode(401).
        when(). get("/service/user");

        expect().statusCode(200).
        when().with().authentication().basic("wadexu", "123456").get("/service/user");
    }
}
