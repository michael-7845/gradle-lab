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
        println l2 in l
        println l.containsAll(l2)
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

    static void main(String... args) {
        demo7()
    }
}