package groovy.lab

/**
 * Created by I340951 on 8/1/2017.
 */
class MyMapLab {
    static void demo1() {
        def l = [ [id: 1, name: "a", value: 'x'],
              [id: 2, name: "b", value: 'y'],
              [id: 3, name: "c", value: 'z'] ]

        def l1 = l.collect { it.id }
        println l1

        def l2 = l.findAll {it.id in [1, 3]}.collect { return it.name }
        println l2

        def l3 = l.collect { if(it.key in ['id', 'value']) return [it] }
        println l3
    }

    static void demo2() {
        def list = [ [id: 1, name: "a"],
                     [id: 2, name: "b"],
                     [id: 3, name: "c"] ]
        def result = list.collect { it.id * it.id }.sum()
        println result
    }

    static void demo3() {
        def list = [unitPrice:11.11, fileName:null, quantity:1, productId:123456780, color:null, lineTotal:11.11, lineDiscount:0.00, modifiedAt:"2017-08-11T08:03:14.709Z", lineSubTotal:11.11, lineStatus:"DRAFT", fulfillmentLocationId:null, size:null, reviewReason:null, orderLineId:[number:1, orderId:5828497407], lineTax:0.00, unitDiscount:0.00, lineTaxPercent:null, invoicedAt:null]
        println list
        println list.unitPrice
    }

    static void demo4() {
        def m1 = [ a: 1, b: 2, c: 3]
        def m2 = [ a: 2, b: 3, c: 4, d: 4]
//        println m1.putAll(m2)
        m2.each { key, value ->
            m1.computeIfAbsent(key, {it ->
                println it;
                return value})
        }
        println m1
    }

    static void demo5() {
        def m1 = [a: 1, b: 2, c: 3]
        def m2 = [a: 2, b: 3, c: 4, d: 4]

        for(item in m1) {
            println item.getKey()
            println item.getValue()
        }
    }

    static void demo6() {
        def m1 = ['a': 'x', 'b': 'y', 'c': 'z']

        for(item in m1) {
            println item.getKey()
            println item.getValue()
            println item.key
            println item.value
        }

        def m2 = ['a': ['loc1': 1, 'loc2': 2],
                  'b': ['loc1': 3, 'loc2': 4],
                  'c': ['loc1': 5, 'loc2': 6]]
        m2.each { product, value ->
            value.each {
                location, number ->
                    println "$product $location $number"
            }
        }
    }

    static void demo7() {
        def m1 = [[1:'loc1'], [2:'loc2'], [3:'loc3']]
        def map = [:]
        m1.each {println it.getClass(); println it.key } //map[it[0].key] = it[0].value}
        println map
    }

    static void demo8() {
        def m1 = [[1:'loc1'], [2:'loc2'], [3:'loc3']]
        def map = [:]
        map = m1.clone()
        map[1] = 'location1'
        m1.each {println it.getClass(); println it.key }
        map.each {println it.getClass(); println it.key } //map[it[0].key] = it[0].value}
        println m1
        println map
    }

    static void demo9() {
        Map<String, Integer> foo = ["foo": 1, "bar": 2]
        Map<String, Integer> bar = foo.getClass().newInstance(foo)
        foo["foo"] = 3
        assert(bar["foo"] == 1)
        assert(foo["foo"] == 3)
        println foo
        println bar
        bar.remove("foo")
        println foo
        println bar
        println bar.containsKey("foo")
    }

    static void demo10() {
        def m1 = [1: 'loc1', 2: 'loc2', 3: 'loc3']
        println ! (1 in m1)

        String s1 = 'a'
        String s2 = 'b'
        String s3 = 'c'
        def m2 = [(s1): 'loc1', (s2): 'loc2', (s3): 'loc3']
        println (s1 in m2)
        println ('a' in m2)
        println ('s1' in m2)
    }

    static void demo11() {
        Map<String, Integer> foo = ["foo": 1, "bar": 2]
        Map<String, Integer> bar = foo.clone()
        foo["foo"] = 3
        assert(bar["foo"] == 1)
        assert(foo["foo"] == 3)
        println foo
        println bar
        bar.remove("foo")
        println foo
        println bar
        println bar.containsKey("foo")
    }

    static void demo12() {
        def m = ['a': ['loc1': 1, 'loc2': 2],
                  'b': ['loc1': 3, 'loc2': 4],
                  'c': ['loc1': 5, 'loc2': 6]]
        def finded = m.find { it.value.find {it.value == 1} }
        println finded
        finded = m.find { it.value.find {it.value == 7} }
        println finded
    }

    static void demo13() {
        def m = ['a': ['loc1': 1, 'loc2': 2],
                 'b': ['loc1': 3, 'loc2': 4],
                 'c': ['loc1': 5, 'loc2': 6],
                 'd': [10,11,12,13,14,15]]
        def m1 = m.clone()
        m1.a = 1
        m1.d = m.d.clone()
        m1.d.remove(0)
        println m
        println m1
        println '--------------------'

        def m2 = m.getClass().newInstance(m)
        m2.a = 1
        m2.d.remove(0)
        println m
        println m2
    }

    static def deepcopy(obj) {
        if(obj instanceof List) {
            List l = obj.clone()
            0.upto(obj.size()-1) {
                l[it] = deepcopy(obj[it])
            }
            return l
        } else if (obj instanceof Map) {
            Map m = obj.clone()
            m.each { key, value ->
                m[key] = deepcopy(value)
            }
            return m
        } else {
            return obj
        }
    }

    static void demo14() {
        def m = ['a': 1, 'b': 2]
        def l = [1, 2]
        def ml = ['a': ['b': [1,2,3]], 'c': [[1,2,3], [4,5,6]]]
//        println m instanceof List
//        println m instanceof Map
//        println m instanceof Iterable
//        println l instanceof List
//        println l instanceof Map
//        println l instanceof Iterable
//
//        def l2 = l.clone()
//        l2[0] = 3
//        println l
//        println l2

        def m2 = deepcopy(m)
        m2.a = 3
        def l2 = deepcopy(l)
        l2[0] = 3
        def ml2 = deepcopy(ml)
        ml2.a.b[0] = 4
        ml2.c[0][0] = 7
        ml2.c[1][0] = 8

        println m2
        println m
        println l2
        println l
        println ml2
        println ml
    }

    static void demo15() {
        def m = ['a': 1, 'b': 2]
        println m.size()
        println 'a' in m
    }

    static void demo16() {
        def ids = [:]
        ids['o1'] = 'abc001'
        ids['o2'] = 'abc002'
        ids['o3'] = 'abc003'

        ids.each { println it.key; println it.value;}

        def shipment_numbers = [:]
        ids.each {
            shipment_numbers[it.key+'_sn'] = it.value+'_1'
        }
        println shipment_numbers
    }

    static void demo17() {
        def m = ['a': ['loc1': 1, 'loc2': 2],
                 'b': ['loc1': 3, 'loc2': 4],
                 'c': ['loc1': 2, 'loc2': 6]]
        def finded = m.findAll { it.key in ['a', 'b'] }
        println finded
        finded = m.findAll { it.value.findAll {it.value == 2} }
        println finded
    }

    static void demo18() {
        def map = [name: 'mrhaki', country: 'The Netherlands', blog: true, languages: ['Groovy', 'Java']]

        def keys = ['name', 'blog']
        assert [name: 'mrhaki', blog: true] == map.subMap(keys)

        def booleanKeys = map.findAll { it.value instanceof Boolean }.collect { it.key }
        assert [blog: true] == map.subMap(booleanKeys)

        def words = ['a': 'Apple', 'j': 'Java', 'g': 'Groovy', 'c': 'Cool']
        def range = 'c'..'h'  // Range is also a list and can be used here.
        def rangeWords = words.subMap(range).findAll{ it.value }
// words.subMap(range) returns [c:Cool, d:null, e:null, f:null, g:Groovy, h:null]
// so we use the findAll method to filter out all null values.
        assert ['c': 'Cool', 'g': 'Groovy'] == rangeWords

        def map2 = [[name: 'mrhaki', country: 'The Netherlands', blog: true, languages: ['Groovy', 'Java']],
                    [name: 'michael', country: 'The China', blog: true, languages: ['Groovy', 'Java', 'Python', 'JavaScript']]]
        println map2.collect { it.subMap(['name', 'languages']) }
    }



    static void main(String... args) {
        demo18()
    }
}
