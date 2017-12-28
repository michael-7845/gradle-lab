package basic.demo;

/**
 * Created by I340951 on 8/29/2017.
 */
public class SystemDemo {
    public static void demo1() {
        System.out.println(System.getenv().get("PRODUCT_BASIC_AUTH"));
    }

    public static void main(String... args) {
        demo1();
    }
}
