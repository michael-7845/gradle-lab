package groovy.dsl.example4

import groovy.dsl.example4.ChecklistDsl

ChecklistDsl.make {
    suite "daily oncall"
    specification "full checkout"
    feature "place order"
    description "Customer place order via pwa, receive order confirmation email."
    orderId "12345678902"
    email "superman@sap.com"
    order(['a', 'b', 1, ])
    content('should have emails': ['order confirmation', 'shipping confirmation', 'delivery notification'],
            'draft order': [total: 85.00, shfee: 10.00 ],
            'clearing': [total: 100.00, tax: 60.00, ],
            'small check points': 'should have icon in cart', )
    toFile 'checklist.html'
    println html
}