package groovy.lab

/**
 * Created by I340951 on 8/1/2017.
 */
class MyLab {
    static void demo1() {
        def l = [ [id: 1, name: "a"],
              [id: 2, name: "b"],
              [id: 3, name: "c"] ]

        def l1 = l.collect { it.id }
        println l1

        def l2 = l.findAll {it.id in [1, 3]}.collect { return it.name }
        println l2
    }

    static void main(args) {
        demo1()
    }
}
