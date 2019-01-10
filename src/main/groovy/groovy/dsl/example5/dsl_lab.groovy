package groovy.dsl.example5

import groovy.dsl.example5.MyDsl

MyDsl.make {
    clients[0].show_name()
    clients[1].show_name()
}