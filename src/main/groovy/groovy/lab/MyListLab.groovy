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

    static void demo2() {
        def list1 = [1,2,3]
        for(it in list1) {
            println it
        }
        println (1 in list1)
    }

    static void demo3() {
        def list1 = [1, 2, 3]
        def list2 = list1.collect{it+3}
        println list2
    }

    static void demo4() {
        def list1 = [1, 2, 3]
        list1.each {
            it -> println it
        }
    }

    static void main(String... args) {
        demo4()
    }
}