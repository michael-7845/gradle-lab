package restassured.demo

import io.restassured.path.json.config.JsonPathConfig

import static io.restassured.RestAssured.*
import static io.restassured.config.JsonConfig.jsonConfig
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*
//import static io.restassured.module.jsv.JsonSchemaValidator.*
import io.restassured.path.json.config.JsonParserType
import io.restassured.response.Response
import spock.lang.Specification

/**
 [origin_title:,
 summary:那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。,
 image:https://img3.doubanio.com/mpic/s1747553.jpg,
 images:[small:https://img3.doubanio.com/spic/s1747553.jpg, large:https://img3.doubanio.com/lpic/s1747553.jpg, medium:https://img3.doubanio.com/mpic/s1747553.jpg],
 author:[[日] 片山恭一],
 catalog:,
 translator:[豫人],
 rating:[average:7.1, min:0, max:10, numRaters:364],
 alt:https://book.douban.com/subject/1220562/,
 binding:平装, title:满月之夜白鲸现,
 url:https://api.douban.com/v2/book/1220562,
 tags:[[count:143, name:片山恭一, title:片山恭一], [count:69, name:日本, title:日本], [count:65, name:日本文学, title:日本文学], [count:41, name:小说, title:小说], [count:33, name:满月之夜白鲸现, title:满月之夜白鲸现], [count:16, name:爱情, title:爱情], [count:10, name:純愛, title:純愛], [count:9, name:外国文学, title:外国文学]],
 alt_title:,
 author_intro:,
 pages:180,
 price:15.00元,
 subtitle:,
 isbn13:9787543632608,
 publisher:青岛出版社,
 isbn10:7543632608,
 id:1220562,
 pubdate:2005-1]
 */
class BasicDemo extends Specification {

    def setup(){
        baseURI = "https://api.douban.com/v2/book";
//        port = 80;
    }

    def "simple get method"() {
        expect:
        Response response = given().when().log().all().post("/1220562").then().body("title", equalTo("满月之夜白鲸现")).extract()
        print response.jsonPath().get();
    }

    def "example 1 - json"() {
        when:
        def response = given()
                .config(config().jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .when().post("/1220562")
        then:
        response.then().body("id", equalTo("1220562"))
//        response.then().body("rating.average", is(new BigDecimal(7.1)))
//        response.then().assertThat().body(matchesJsonSchemaInClasspath("products-schema.json"));
//        response.then().body('$', hasItems(1, 2, 3)) // An empty string "" would work as well
    }

    def "example 3 - complex parsing and validation"() {
        expect:
        given().when().post("/1220562").then().body("tags.findAll { it.count < 20 }.title", hasItems("爱情", "純愛", "外国文学"));
        given().when().post("/1220562").then().body("tags.collect { it.count }.sum()", greaterThan(50));
    }
}
