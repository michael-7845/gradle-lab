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
 * {"rating":{"max":10,"numRaters":364,"average":"7.1","min":0},"subtitle":"","author":["[日] 片山恭一"],"pubdate":"2005-1","tags":[{"count":143,"name":"片山恭一","title":"片山恭一"},{"count":69,"name":"日本","title":"日本"},{"count":65,"name":"日本文学","title":"日本文学"},{"count":41,"name":"小说","title":"小说"},{"count":33,"name":"满月之夜白鲸现","title":"满月之夜白鲸现"},{"count":16,"name":"爱情","title":"爱情"},{"count":10,"name":"純愛","title":"純愛"},{"count":9,"name":"外国文学","title":"外国文学"}],"origin_title":"","image":"https://img3.doubanio.com\/mpic\/s1747553.jpg","binding":"平装","translator":["豫人"],"catalog":"\n      ","pages":"180","images":{"small":"https://img3.doubanio.com\/spic\/s1747553.jpg","large":"https://img3.doubanio.com\/lpic\/s1747553.jpg","medium":"https://img3.doubanio.com\/mpic\/s1747553.jpg"},"alt":"https:\/\/book.douban.com\/subject\/1220562\/","id":"1220562","publisher":"青岛出版社","isbn10":"7543632608","isbn13":"9787543632608","title":"满月之夜白鲸现","url":"https:\/\/api.douban.com\/v2\/book\/1220562","alt_title":"","author_intro":"","summary":"那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。","price":"15.00元"}
 */
class JsonPathDemo extends Douban {
    def "how to use RestAssured JsonPath"() {
        expect:
        Response response = given().when().log().all().get("/book/1220562");
        println response.asString()
        assertEquals(200, response.getStatusCode());
        String json = response.asString();
        JsonPath jp = new JsonPath(json);
        assertEquals(10, jp.get("rating.max"));
        assertEquals("7.1", jp.get("rating.average"));
        assertEquals("", jp.get("subtitle"));
        assertEquals(143, jp.get("tags[0].count"));
    }
}
