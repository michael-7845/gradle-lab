package groovy.lab

/**
 * Created by I340951 on 8/14/2017.
 */
public class MyListLab {
    static void demo1() {
        def list1 = [1, 2, 3]
        def list2 = [3, 4, 5]
        println list1.disjoint([3, 4])
        println list1.intersect(list2)
        println 123

    }

    static void main(String... args) {
        demo1()
    }
}