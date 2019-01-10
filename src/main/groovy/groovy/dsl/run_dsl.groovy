package groovy.dsl

import groovy.dsl.example4.ParseDsl

ParseDsl.make {
    to "Nirav Assar"
    from "Barack Obama"
    body "How are things? We are doing well. Take care"
    idea "The economy is key"
    request "Please vote for me"
    html
}