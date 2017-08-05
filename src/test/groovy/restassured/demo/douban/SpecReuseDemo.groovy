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
class SpecReuseDemo extends Douban {
    def "how to reuse spec."() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectBody("userInfo.userId", equalTo("wadexu"));
        builder.expectBody("userInfo.firstName", equalTo("Wade"));
        builder.expectBody("userInfo.lastName", equalTo("Xu"));
        builder.expectBody("success", equalTo(true));
        ResponseSpecification responseSpec = builder.build();
        //use this specification for test example -- a
        expect().spec(responseSpec)
        .when().get("/user/login?userName=wadexu&password=NzrmRcIfIW4=");
        //now re-use for another example -- c that returns similar data
        given().queryParam("userName", "wadexu").queryParam("password", "NzrmRcIfIW4=")
        .expect().spec(responseSpec)
        .when().get("/user/login");
    }
}
