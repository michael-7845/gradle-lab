package basic.demo;

/**
 * Created by I340951 on 11/16/2017.
 */
public class JStringDemo {
    public static void demo1() {
        String f = "123%s456";
        System.out.println(String.format(f, "abc"));
    }

    public static void main(String[] args) {
        demo1();
    }
}
