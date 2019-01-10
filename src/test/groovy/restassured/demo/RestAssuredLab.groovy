package restassured.demo

import static io.restassured.RestAssured.*
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.equalTo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class RestAssuredLab {
    static void lab1() {
        given().header("Authorization", "Basic c2ItYXVkaXRsb2ctYXBpLWFwcHJvdXRlciFiNjU2fGF1ZGl0bG9nLWFwaSFiNjYyOmgwTlVXcUZyMXphSXpnbGV2WnljR1ROaGVJbz0=")
                .log().all()
                .queryParam("grant_type", 'client_credentials')
                .queryParam("authorities", '{"az_attr":{"subscriberaccountid":"automation-test-integration"}}')
                .when().post("https://caastest.authentication.us10.hana.ondemand.com/oauth/token")
                .then().log().all()
    }

    static void main(String... args) {
        lab1()
    }
}
