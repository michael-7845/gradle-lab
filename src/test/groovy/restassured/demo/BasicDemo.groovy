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
 summary:��һ�꣬����Ī���ء�������ͼ�ͥ���ѵ�һ�ꡣ˵����ͥ���ѣ�ĸ�׹��Լ�����û���ҵ������ˣ���������Ϊ��ʱ�Ǳ����꾫��ס���ۣ�ʧ������ĸ�ף�����������Ǹ��ס�����,
 image:https://img3.doubanio.com/mpic/s1747553.jpg,
 images:[small:https://img3.doubanio.com/spic/s1747553.jpg, large:https://img3.doubanio.com/lpic/s1747553.jpg, medium:https://img3.doubanio.com/mpic/s1747553.jpg],
 author:[[��] Ƭɽ��һ],
 catalog: ,
 translator:[ԥ��],
 rating:[average:7.1, min:0, max:10, numRaters:364],
 alt:https://book.douban.com/subject/1220562/,
 binding:ƽװ,
 title:����֮ҹ�׾���,
 url:https://api.douban.com/v2/book/1220562,
 tags:[[count:143, name:Ƭɽ��һ, title:Ƭɽ��һ], [count:69, name:�ձ�, title:�ձ�], [count:65, name:�ձ���ѧ, title:�ձ���ѧ], [count:41, name:С˵, title:С˵], [count:33, name:����֮ҹ�׾���, title:����֮ҹ�׾���], [count:16, name:����, title:����], [count:10, name:����, title:����], [count:9, name:�����ѧ, title:�����ѧ]],
 alt_title:,
 author_intro:,
 pages:180,
 price:15.00Ԫ,
 subtitle:,
 isbn13:9787543632608,
 publisher:�ൺ������,
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
        Response response = given().when().log().all().post("/1220562").then().extract() //.body("title", equalTo("����֮ҹ�׾���"));
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
}
