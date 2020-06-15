package groovy.dsl.example4

import groovy.dsl.example4.CheckList

CheckList.make {
    suite "daily oncall"
    specification "full checkout"
    feature "place order"
    description "Customer place order via pwa, receive order confirmation email."
    orderId "12345678902"
    email "superman@sap.com"
    order('draft': [total: 85.00, shfee: 10.00 ],
            'clearing': [total: 100.00, tax: 60.00, ],)
    checklist('should have emails': ['order confirmation', 'shipping confirmation', 'delivery notification'],
            'small check points': ['should have icon in cart', 'should empty cart after placing order', ] )
    toFile 'checklist.html'
    println html
}