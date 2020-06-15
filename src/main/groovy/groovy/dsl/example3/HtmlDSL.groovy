package groovy.dsl.example3

import javax.swing.text.html.HTML

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class HtmlDSL {
    def invokeMethod(String name, args) {
        print "<${name}"
        args.each { arg ->
            if (arg instanceof Map) {
                arg.each {
                    print " ${it.key} ='${it.value}' "
                }
            } else if (arg instanceof Closure) {
                print '>'
                arg.delegate = this
                def value = arg.call()
                if (value) {
                    print "${value}"
                }
            }
        }
        println "</${name}>"
    }

    def static make(closure) {
        HtmlDSL htmlDsl = new HtmlDSL()
        closure.delegate = htmlDsl
        closure()
    }

    static def lab1() {
        HtmlDSL.make {
            html {
                head {
                    meta {
                    }
                }
                body {
                    table (style:'margin:5px;') {
                        tr ('class':'trClass', style:'padding:5px;') {
                            td {'Cell1'}
                            td {'Cell2'}
                            td {'Cell3'}
                        }
                    }
                }
            }
        }
    }

    static void main(String... args) { lab1() }
}
