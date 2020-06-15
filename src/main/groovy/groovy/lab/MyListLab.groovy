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
        println list1 + list2

        def list3 = [1768214176, 6683263259, 2434702384, 5228551570]
        def list4 = [6683263259, 1768214176, '0566563549', '0090141174', 9826962441, 5955820869, 6840565666, 6018585033, 7465591254, 3642459138]
        println list3.disjoint(list4)
        println list3.intersect(list4)
        def list5 = list4.intersect(list3)
        println list3 - list5

        def list6 = ['0181716253', 2320761013, 7343303823, 4290705254, 9936500518, 8531596732]
        def list7 = [9961662986, 8442332144, 6683263259, 1768214176, '0566563549', '0090141174', 9826962441, 5955820869, 6840565666, 6018585033]
        println list6.disjoint(list7)
        println list6.intersect(list7)
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

        println list1
        println list1.collect {it+1}
        println list1
    }

    static void demo4() {
        def list1 = [1, 2, 3]
        list1.each {
            it -> println it
        }

        println list1
        println list1.each {it+1}
        println list1
    }

    static void demo5() {
        List l = []
        l << ['a':1]
        l << ['b':2]
        l << ['c':3]
        println l
    }

    static void demo6() {
        List l = [1, 2, 3, 4, 5]
        List l2 = [1, 3, 5]
        List l3 = [1, 5, 3]
        println l2 in l
        println l.containsAll(l2)
        println l.containsAll(l3)
    }

    static void demo7() {
        def list = ['a', 'b', 'c']
        list.eachWithIndex { item, index ->
            println item
            println index
        }

        // With Groovy 2.4 and newer, you can also use the indexed() method.
        // This can be handy to access the index with methods like collect:
        def result = list.indexed().collect { index, item ->
            "$index: $item"
        }
        println result

        println result[1..2]
    }

    static void demo8() {
        def list = ['Daffy', 'Bugs', 'Elmer', 'Tweety', 'Silvester', 'Yosemite']
        assert 'Bugs' == list.find { it == 'Bugs' }
        assert ['Daffy', 'Bugs', 'Elmer'] == list.findAll { it.size() < 6 }
        assert 1 == list.findIndexOf { name -> name =~ /^B.*/ }  // Start with B.
        assert 3 == list.findIndexOf(3) { it[0] > 'S' }  // Use a start index.
        assert [0,3,5] == list.findIndexValues { it =~ /(y|Y)/ }  // Contains y or Y.
        assert [3,5] == list.findIndexValues(2) { it =~ /(y|Y)/ }
        assert 2 == list.findLastIndexOf { it.size() == 5 }
        assert 5 == list.findLastIndexOf(1) { it.count('e') > 1 }
        assert list.any { it =~ /a/ }
        assert list.every { it.size() > 3 }

        def map = [name: 'Messages from mrhaki', url: 'http://mrhaki.blogspot.com', blog: true]
        def found = map.find { key, value -> key == 'name' }
        assert found.key == 'name' && found.value == 'Messages from mrhaki'
        found = map.find { it.value =~ /mrhaki/ }
        assert found.key == 'name' && found.value == 'Messages from mrhaki'
        assert [name: 'Messages from mrhaki', url: 'http://mrhaki.blogspot.com'] == map.findAll { key, value -> value =~ /mrhaki/ }
        assert 1 == map.findIndexOf { it.value.endsWith('com') }
        assert [1,2] == map.findIndexValues { it.key =~ /l/ }  // All keys with the letter 'l'.
        assert 2 == map.findLastIndexOf { it.key =~ /l/ && it.value }
        assert map.any { entry -> entry.value }
        assert map.every { key, value -> key.size() >= 3 }
    }

    static void demo9() {
        def list = ['Daffy', 'Bugs', 'Elmer', 'Tweety', 'Silvester', 'Yosemite']
        println list[0]
        println list.get(0)
    }

    static void demo10() {
        def list = []
        def nothing = null
        def l1 = []
        def l2 = [1]
        def l3 = [2,3]
        list.addAll(nothing ?: [])
        list.addAll(l1 ?: [])
        list.addAll(l2 ?: [])
        list.addAll(l3 ?: [])
        println list
    }

    // remove duplicated item
    static void demo11() {
        List<String> li1 = ["8", "8", "9", "9" ,"0","9"]
        List<String> li2 = ["张三", "张三", "李四", "张三", "王五", "李四"]
        List<String> li3 = ["A", "A", "C", "A", "C", "D"]
        List<String> li4 = ["12", "18", "19", "19", "10", "19"]

        HashSet<String> hs = new HashSet<String>(li1)
        println hs
        println hs.toList()

        hs.each { println it }
    }

    static void demo12() {
        def l = [7864, 284, 347, 7732, 8498]
        StringBuilder sb = new StringBuilder()
        l.sort {a, b -> a.toString() < b.toString() ? 1: a.toString() < b.toString() ? 0 : -1}
         .each { sb.append(it) }
        println sb.toString()
    }

    static void demo13() {
        List l = ['a', 'b', 'c']
        String s1 = String.join(',', l)
        println s1
        println String.join(',', ['a'])
        String[] s2 = s1.split(',', 2) //"boo:and:foo".split(':')
        println s2
        s2.each { println it }
    }

    static void demo14() {
//        println 11 % 5
//        println Math.floorDiv(11, 5)
        int n = 3
        List l = [0,1,2,3,4,5,6,7,8,9,10,11,12,13]
        println slice_list(l, n)
        l = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]
        println slice_list(l, n)
        l = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        println slice_list(l, n)
        l = [0,1,2,3]
        println slice_list(l, n)
        l = [0,1,2,3,4]
        println slice_list(l, n)
        l = [0,1,2,3,4,5]
        println slice_list(l, n)
        l = []
        println slice_list(l, n)
    }

    static List slice_list(List l, Integer n) {
        println '-------------- slice_list()'
        assert n>0
        def _size = l.size()
        def _remainder = _size % n
        def _mode = Math.floorDiv(_size, n)
        println "remainder : ${_remainder}, mode : ${_mode}"

        List result = []
        (_mode).times {
            println "${it*n} - ${(it+1)*n-1}"
            result << l.getAt((it*n)..((it+1)*n-1))
        }
        if(_remainder>0) {
            println "${(_mode)*n} - ${l.size()-1}"
            result << l.getAt((_mode*n)..(_size-1))
        }

        result
    }

    static List _slice_list(List l, Integer n) {
        assert n>0
        def _size = l.size()
        def _remainder = _size % n
        def _mode = Math.floorDiv(_size, n)

        List result = []
        (_mode).times { result << l.getAt((it*n)..((it+1)*n-1)) }
        if(_remainder>0) {result << l.getAt((_mode*n)..(_size-1)) }

        result
    }

    static void demo15() {
        List l = [[number: 1, message: 'process 1'],
                  [number: 3, message: 'process 2'],
                  [number: 2, message: 'process 3']]
        println l.findIndexOf { it.message == 'process 2'}

        l.sort{a, b -> a.number < b.number ? -1 : a.number > b.number ? 1 : 0}.each {println it}

        l.eachWithIndex{  entry, i ->
            println "entry: ${entry}, index: ${i}"
        }
    }

    static void demo16() {
        List l = [1, 2, 3, 4, 5]
        println l - [2]
        println l - l[1..<2]
    }

    static void demo17() {
        List a3 = [[id:'a', qty:1], [id:'a', qty:1], [id:'a', qty:1], ]
        List b2 = [[id:'b', qty:2], [id:'b', qty:2], ]
        List c1 = [[id:'c', qty:1], ]
        List l = [a3, b2]
        println l
        println l.flatten()

//        List l2 = [c1]
//        println l2
//        println l2.flatten()
//
//        println c1.flatten()
//
//        1.upto(3) {println it}
//        1.upto(1) {println it}
//        1.upto(0) {println it}
//        1.upto(-1) {println it}
        0.upto(2) {println it}
        3.times { println it }
    }

    static void demo18() {
        List l = [false, true, false, ]
        Map m = [a: false, b: true, c: false, ]

        println l.every {it}
        println l.any {it}
        println m.every {it.value}
        println m.any {it.value}
    }

    static void demo19() {
        List<Map> lm = [[a:1, b:1, c:1], [a:2, b:2, c:2], [a:3, b:3, c:3],]
        println ([a:1, b:1, c:1] in lm)
        println ([a:2, b:2, c:3] in lm)
        println ([a:2, b:2] in lm)
    }

    static void demo20() {
        List l = [1,2,3,4,5,6]
        println l.find { it > 2 }
        println l.find { it > 6 }
        println l.findAll { it > 2 }
        println l.findAll { it > 6 }
    }

    static void main(String... args) {
        demo17()
    }
}