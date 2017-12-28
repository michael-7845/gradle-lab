package basic.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by I340951 on 10/17/2017.
 */
public class JSetDemo {
    public static void demo1() {
        Set<String> s = new HashSet<String>();
        s.add("abc");
        s.add("ab");
        s.add("a");
        s.add("ab");
        System.out.println(s);
    }

    public static void demo2() {
        Set<String> s = new HashSet<String>();
        s.add("abc");
        s.add("ab");
        s.add("a");
        s.add("ab");
        System.out.println(s);
        List<String> l = new ArrayList<String>();
        l.addAll(s);
        System.out.println(l);
    }

    public static void main(String[] args) {
        demo2();
    }
}
