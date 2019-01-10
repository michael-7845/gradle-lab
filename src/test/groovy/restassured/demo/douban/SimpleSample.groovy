package restassured.demo.douban

import io.restassured.response.Response

import static io.restassured.RestAssured.*
import static io.restassured.config.JsonConfig.jsonConfig
import static io.restassured.matcher.RestAssuredMatchers.*

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class SimpleSample {
    static void demo1() {
        Response r = get('http://10.47.41.216:9999/tokens/automation-test-default')
        println r.asString()
        println r.jsonPath().getString('merchant_access_token')
        println r.jsonPath().getString('buyer_access_token')
    }

    static void main(String... args) {
        demo1()
    }
}
