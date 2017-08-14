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

    static void main(String... args) {
        demo3()
    }
}
