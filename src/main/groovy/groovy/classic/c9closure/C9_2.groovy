package groovy.classic.c9closure

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class C9_2 {
    static void sample8() {
        [1,2,3,4].each {println it}
        ['Ken':21, 'John':22, 'Sally':25].each {println it}
        ['Ken':21, 'John':22, 'Sally':25].each {println "${it.key} maps to: ${it.value}"}
    }

    static void sample9() {
        [1,2,3,4].each {num -> if(num%2 == 0)println num}
        ['Ken':21, 'John':22, 'Sally':25].each {staff -> if(staff.value >= 25)println staff.key}
        ['Ken':21, 'John':22, 'Sally':25].each {name,age -> if(age >= 25)println name}
    }

    static void sample10() {
        def value = [1,3,5,7,9].find {element -> element > 6}
        println "Found: ${value}"

        value = [1,3,5,7,9].find {element -> element > 10}
        println "Found: ${value}"

        value = ['Ken':21, 'John':22, 'Sally':25].find {staff -> staff.value >= 21}
//        value = ['Ken':21, 'John':22, 'Sally':25].find {key,value -> value >= 21} //error
        println "Found: ${value}"
    }

    static void sample11() {
        def value = [1,3,5,7,9].findAll {element -> element > 6}
        println "Found: ${value}"

        [1,3,5,7,9].findAll {element -> element > 6}.each {println it}

        value = ['Ken':21, 'John':22, 'Sally':25].findAll {staff -> staff.value >= 22}
//        value = ['Ken':21, 'John':22, 'Sally':25].findAll {key,value -> value >= 21} //error
        value.each {println it}
    }

    static void sample12() {
        def anyElement = [11,12,13,14].any {element -> element > 12}
        println "anyElement: ${anyElement}"

        def allElement = [11,12,13,14].every {element -> element > 10}
        println "allElement: ${allElement}"

        def anyStaff = ['Ken':21, 'John':22, 'Sally':25].any {staff -> staff.value > 30}
        println "anyStaff: ${anyStaff}"
        anyStaff = ['Ken':21, 'John':22, 'Sally':25].any {name,age -> age > 30}
        println "anyStaff: ${anyStaff}"
    }

    static void sample13() {
        def list = [1,2,3,4].collect {element -> return element * element}
        println "list: ${list}"

        list = [1,2,3,4].collect {element -> element * element}
        println "list: ${list}"

        list = (0..<5).collect {element -> 2 * element}
        println "list: ${list}"

        def staff = ['Ken':21, 'John':22, 'Sally':25]
        list = staff.collect {entry -> ++entry.value}
        def oldStaff = staff.collect {entry -> ++entry.value; return entry}
        println "staff: ${staff}"
        println "list: ${list}"
        println "oldStaff: ${oldStaff}"
    }

    static def map(clos, list) {
        return list.collect(clos)
    }

    static void sample14() {
        def doubles = {item -> 2*item}
        def triples = {item -> 3*item}
        def isEven = {item -> (item % 2 == 0) }

        println "Doubling: ${map(doubles,[1,2,3,4])}"
        println "Tripling: ${map(triples,[1,2,3,4])}"
        println "Evens: ${map(isEven,[1,2,3,4])}"
    }

    static void sample15() {
        //Direct usage
        def factorial = [2,3,4,5].inject(1) {previous,element -> previous * element}
        println "Factorial(5): ${factorial}"

        //Equivalence
        def fact = 1
        [2,3,4,5].each {number -> fact *= number}
        println "fact: ${fact}"

        //Named list and closure
        def list = [2,3,4,5]
        factorial = list.inject(1) {previous,element -> previous * element}
        println "Factorial(5): ${factorial}"

        //Named list and closure
        list = [2,3,4,5]
        def closure = {previous,element -> previous * element}
        factorial = list.inject(1, closure)
        println "Factorial(5): ${factorial}"

    }

    static void _main() {
        sample15()
    }

    static void main(String... args) {
        _main()
    }
}
