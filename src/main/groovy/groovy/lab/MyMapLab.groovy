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
        String s4 = 'abc'
        def m2 = [(s1): 'loc1', (s2): 'loc2', (s3): 'loc3', abc: 'loc4']
        println (s1 in m2)
        println ('a' in m2)
        println ('s1' in m2)
        println (s4 in m2)
        println ('abc' in m2)
    }

    static void demo11() {
        Map<String, Integer> foo = ["foo": 1, "bar": 2, "level_a": ["foo": 1, "bar": 2]]
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

        bar.level_a.remove("foo")
        println foo
        println bar
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

    static void demo19() {
        Map m1 = [a: 1, b:2, c:3, d:4, e:5, f:6, g:7]
        Map m2 = m1.subMap(m1.keySet().toList() - 'g')
        println m1
        println m2
        m1.remove('d')
        println m1
        println m2
    }

    static void demo20() {
        Map m1 = [a: 1, b:2, c:3, d:4, e:5, f:6, g:7]
        Map m2 = [a: 1, b:2, c:3, d:4, e:5, f:6] // - g:7
        Map m3 = [a: 1, b:2, c:3, d:4, e:5, f:6, g:7, h:8] // + h:8
        Map m4 = [a: 1, b:2, c:3, d:4, e:5, f:6, h:8]      // - g:7, + h:8
        Map m5 = [:]

        delta(m1, m2)
        delta(m1, m3)
        delta(m1, m4)
        delta(m1, m5)
    }

    static void delta(Map d1, Map d2) {
        println '============== delta: '
        println "d1: ${d1}"
        println "d2: ${d2}"
        println 'd1-d2: '+ (d1.keySet() - d2.keySet())
        println 'd2-d1: '+ (d2.keySet() - d1.keySet())
        def _set = d1.keySet() + d2.keySet()
        _set.each { key ->
            if(d1[(key)] != d2[(key)]) {
                println "key -> d1 - ${key}:${d1[(key)]} , d2 - ${key}:${d2[(key)]}"
            }
        }
    }

    static void demo21() {
        map_arguments(a:1, b:2, 'michael')
        map_arguments(null, 'michael')
        map_arguments2('michael', [a:1, b:2])
    }

    static void map_arguments(Map params, String s, Boolean b = true) {
        println "params: " + params
        println "s: " + s
        println "b: " + b
    }

    static void map_arguments2(String s, Map params) {
        println "params: " + params
        println "s: " + s
    }

    static void demo22() {
        Map m1 = [a:1, b:2, c:3]
        Map m2 = [b:4]
        Map m3 = [d:5]
        println '-----------'
        println m1.putAll(m2)
        println m1
        m1.put('e', 5)
        println '-----------'
        println m1
//        println m1.put('f', 6)
        println m1+[f:6]
        println '-----------'
        println m1
        println ([a:1, b:2, c:3] + [b:4])
        println m1+m3
        println m1
        println m3
    }

    static void demo23() {
        Map m1 = [a:[x:1, y:2, z:3], b:[x:10, y:20, z:30], c:[x:-1, y:-2, z:-3]]

        println m1.find{key,value -> value.x == 1}.key
        println m1.find{it.value.x == 1}.key

        println m1.findAll{key,value -> value?.x > 0}
        println m1.findAll{key,value -> value?.x > 10}
    }

    static void demo24() {
        Map m1 = [a:1, b:2, c:3]
        println m1.subMap(m1.keySet() - ['c'])
    }

    static void demo25() {
        Map m1 = [a:1, b:2, c:3, d:null]
        println m1
        println m1.a
        println m1.d
        println m1.e
        println m1.findAll {it.value != null}
        println m1.findAll {it.value}
    }

    static Boolean check_condition(Map params) {
        Boolean should_throw_exception = false
        if((!('index' in params)) &&
                ((!('order_id' in params)) || (!('shipment' in params))) ) {
            should_throw_exception = true
        }
        should_throw_exception
    }

    static void demo26() {
        Map params
        params = [index: 1]
        println "${params} - ${check_condition(params)}"
        params = [order_id: 'abc', shipment: 'ABC']
        println "${params} - ${check_condition(params)}"
        params = [wait: false]
        println "${params} - ${check_condition(params)}"
        params = [order_id: 'abc']
        println "${params} - ${check_condition(params)}"
        params = [shipment: 'ABC']
        println "${params} - ${check_condition(params)}"
        params = [index: 1, order_id: 'abc']
        println "${params} - ${check_condition(params)}"
    }

    static def demo27() {
        // value is 0 or true or null or '' or things alike, key in map return false
        def m = [selected:0, continue:false, 1:'a', 'b':2]
//        m.each { key, value ->
//            println key
//            println key.getClass()
//            println value
//            println value.getClass()
//        }
        println ('selected' in m) // has 'selected' key, but its value is 0/false -> false
        println ('selected' in m.keySet())
        println ('continue' in m) // has 'continue' key, but its value is 0/false -> false
        println ('continue' in m.keySet())
        println (1 in m)
        println ('b' in m)

        def x = [a: 1, b: 2]
        def y = [a: 1, b: 2, c: 3]
        println (x in y)
        println x.keySet()
        println y.keySet()
        println (x.keySet() in y.keySet())
        println (y.keySet().contains(x.keySet()))
        println (y.keySet().containsAll(x.keySet()))
    }


    static List find2(String str, String pattern) {
        def m = str =~ pattern
        if(m.find()) {
            return m[0]
        }
        return null
    }

    static def demo28() {
        List l1 = [
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username.html",
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username.png",
        ]
        List l2 = [
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_wb_.html",
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_wb_.png",
        ]
        List l3 = [
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_wb_.html",
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_wb_.png",
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_pwa_.html",
                "com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_pwa_.png"
        ]
//        List l = l1

        [l1, l2, l3]. each { l ->
            println "--------------- ${l}"
            def image, html, browsers
            browsers = l.collect { def m = it =~ /_(\w+)_.\w+/; (m.find()) ? m[0][1] : '' }.unique()
            println l
            println browsers

            browsers.each { b ->
                def _postfix = ''
                if(b != '') _postfix = "_${b}_"
                image = l.find { it.endsWith("${_postfix}.png") }
                html = l.find { it.endsWith("${_postfix}.html") }
                println b
                println image
                println html
            }
        }


//        if(browsers.size() > 1) {
//            browsers.each { b ->
//                image = l1.findAll { it.endsWith("_${b}_.png") }
//                html = l1.findAll { it.endsWith("_${b}_.html") }
//                println image
//                println html
//            }
//        } else {
//            image = l1.findAll { it.endsWith('.png') }
//            html = l1.findAll { it.endsWith('.html') }
//            println image
//            println html
//        }

    }

    /*
resource:
  candidate:
    job_names: [Daily Export Job, Payment settlement, Order Backend Job]
    reasons:
      cancel: ['1', '2', '3', '4', '5']
      return: ['001', '002', '003', '004']
      mispick: ['001', '002']
    locations: [boston-1, california-1, newjersey-1, phoenix-1, ca-store-1, ma-store-1]
    experience_names: [Upscale Mobile Native Shopping Experience]
     */
    static def demo29() {
        Map skeleton
//        skeleton = [:]
        skeleton =
        [resource: [candidate:
                            [job_name:[1,2,3,],
                             reason:[cancel:[1,2,3,],
//                                     return:[1,2,3,],
                                     mispick:[1,2,3,]],
//                             location_name:[1,2,3,],
                             experience_name:[1,2,3,]],
                    shipping:[method:[ca:[1,2,3,],
                                      ny:[1,2,3,]]],
                    job:[settle:[1,2,3,],
//                         export:[1,2,3,],
//                         continuity:[1,2,3,],
                         refund:[1,2,3,]],
                    reason:[cancel:[1,2,3,],
//                            return:[1,2,3,],
                            mispick:[1,2,3,]],
                    location:[warehouse:[1,2,3,],
//                              store:[1,2,3,],
                              fulfillment_store:[1,2,3,]],
//                    inventory_quota:0,
//                    experience:[1,2,3,],
//                    soc:[1,2,3,],
                    product:[regular:[1,2,3,],
                             variant:[[a:1], [b:2], [c:3]]]]]

        // 1st level
        skeleton.putIfAbsent('resource', [:])

        // 2nd level
        skeleton.resource.putIfAbsent('candidate', [:])
        skeleton.resource.putIfAbsent('shipping', [:])
        skeleton.resource.putIfAbsent('job', [:])
        skeleton.resource.putIfAbsent('reason', [:])
        skeleton.resource.putIfAbsent('location', [:])
        skeleton.resource.putIfAbsent('inventory_quota', 0)
        skeleton.resource.putIfAbsent('experience', [])
        skeleton.resource.putIfAbsent('soc', [])
        skeleton.resource.putIfAbsent('product', [:])

        // 3rd and 4th level
        // candidate
        skeleton.resource.candidate.putIfAbsent('job_name', [])
        skeleton.resource.candidate.putIfAbsent('reason', [:])
        skeleton.resource.candidate.reason.putIfAbsent('cancel', [])
        skeleton.resource.candidate.reason.putIfAbsent('return', [])
        skeleton.resource.candidate.reason.putIfAbsent('mispick', [])
        skeleton.resource.candidate.putIfAbsent('location_name', [])
        skeleton.resource.candidate.putIfAbsent('experience_name', [])
        // shipping
        skeleton.resource.shipping.putIfAbsent('method', [:])
        skeleton.resource.shipping.method.putIfAbsent('ca', [])
        skeleton.resource.shipping.method.putIfAbsent('ny', [])
        // job
        skeleton.resource.job.putIfAbsent('settle', [])
        skeleton.resource.job.putIfAbsent('export', [])
        skeleton.resource.job.putIfAbsent('continuity', [])
        skeleton.resource.job.putIfAbsent('refund', [])
        // reason
        skeleton.resource.reason.putIfAbsent('cancel', [])
        skeleton.resource.reason.putIfAbsent('return', [])
        skeleton.resource.reason.putIfAbsent('mispick', [])
        // location
        skeleton.resource.location.putIfAbsent('warehouse', [])
        skeleton.resource.location.putIfAbsent('store', [])
        skeleton.resource.location.putIfAbsent('fulfillment_store', [])
        // experience, only 2nd level
        // soc, only 2nd leve
        // product
        LinkedHashMap
        skeleton.resource.product.putIfAbsent('regular', [])
        skeleton.resource.product.putIfAbsent('variant', [])
        Integer _current = skeleton.resource.product.variant.size()
        Integer _target = 3
//        ((_target - _current)>=0 ? (_target - _current) : 0).times {
        (_target - _current).times {
            skeleton.resource.product.variant << [:]
        }

        println skeleton
    }

    static demo30() {
        Map m = [ca: [a: 1, b: 1, c: 1,], ny: [a: 2, b: 2, c: 2,], or: [a: 3, b: 3, c: 3,],]
//        println m.collect { if(it.value > 2) it }
        println m.find { it.key == 'ca' }
        println m.findAll { it.key != 'ca' }

        def name = 'ca.a'
        println m[(name)]
    }

    static void main(String... args) {
        demo30()
    }
}
