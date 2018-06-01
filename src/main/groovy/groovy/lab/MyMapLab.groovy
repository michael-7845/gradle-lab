package groovy.lab

/**
 * Created by I340951 on 8/1/2017.
 */
class MyMapLab {
    static void demo1() {
        def l = [ [id: 1, name: "a"],
              [id: 2, name: "b"],
              [id: 3, name: "c"] ]

        def l1 = l.collect { it.id }
        println l1

        def l2 = l.findAll {it.id in [1, 3]}.collect { return it.name }
        println l2
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

    static void main(String... args) {
        demo6()
    }
}
