package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MySetLab {
    static void demo1() {
        def l = [1, 2, 3]
        def l2 = [2, 3, 1]
        def l3 = ["1", '2', '''3''']
        Set s = new HashSet(l)
        Set s2 = new HashSet(l2)
        Set s3 = new HashSet(l3)
        Set s4 = new HashSet()
        s4.add(3)
        s4.add(1)
        s4.add(2)
        println s
        println s2
        println s3
        println s4
        println s == s2
        println s == s3
        println s == s4
    }

    static void demo2() {
        def l = [1, 2, 3]
        def l2 = [2, 3, 1]
        def l3 = ["1", '2', '''3''']
        Set s = new TreeSet(l)
        Set s2 = new TreeSet(l2)
        Set s3 = new TreeSet(l3)
        Set s4 = new TreeSet()
        s4.add(3)
        s4.add(1)
        s4.add(2)
        println s
        println s2
        println s3
        println s4
        println s == s2
        println s == s3
        println s == s4

        println 1 in s

        println s.containsAll(l2)
        println s.containsAll([2,1,3])
    }

    static void demo3() {
        def l1 = [1, 2, 3, 4]
        def l2 = [2, 3, 1, 5]
        def l3 = ["1", '2', '''3''']

        def s1 = l1.toSet()
        def s2 = l2.toSet()
        def s3 = l3.toSet()

        println s1 - s2
        println s1 + s2
        println s1.disjoint(s2)
        println s1.intersect(s2)
    }

    static void demo4() {
        Map params = ['tech_token': 1, 'env': 2, 'tenant':3, 'email': 4, 'password': 5, ]
        Map params2 = ['tech_token': 1, 'env': 2, 'tenant':3, 'email': 4, 'password': 5, extra: 6]

        Set mandatory_fields = ['tech_token', 'env', 'tenant', 'email', 'password']
        Set missing_fields = mandatory_fields - params.keySet()
        println missing_fields
        println mandatory_fields - params2.keySet()
        println params2.subMap(mandatory_fields)
        if(missing_fields.size()>0) { println missing_fields}
    }


    static void _main() {
        demo4()
    }

    static void main(String... args) {
        _main()
    }
}
