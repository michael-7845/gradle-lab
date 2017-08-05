package restassured.demo.douban

import spock.lang.Specification

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
 * http://blog.csdn.net/gsying1474/article/details/51332371
 */
class Douban extends Specification {

    def setup() {
        RestAssured.baseURI= "https://api.douban.com";
//        RestAssured.port = 8080;
        RestAssured.basePath = "/v2";
    }

}
