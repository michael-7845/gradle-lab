package groovy.lab

import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.codehaus.groovy.runtime.EncodingGroovyMethods
import org.codehaus.groovy.runtime.IOGroovyMethods
import org.codehaus.groovy.runtime.ResourceGroovyMethods
import org.codehaus.groovy.runtime.StringGroovyMethods

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyGroovyBasicLab {
    // important core groovy class
    DefaultGroovyMethods dgm = 1
    StringGroovyMethods sgm = 'a'
    ResourceGroovyMethods rgm = 1
    IOGroovyMethods igm = 1
    EncodingGroovyMethods egm = 1

    static void demo1() {
        def a = 1, b = 2
        println a.is(b)

        a = [1,2,3,2]
        println a.unique()
        a = a.toArray()
        println a.toUnique()
    }

    static void demo2() {

    }

    static void _main() {
        demo1()
    }

    static void main(String... args) {
        _main()
    }
}
