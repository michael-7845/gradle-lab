package groovy.dsl.mopdemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://blog.csdn.net/a568478312/article/details/79912881
 */
class MopDemo4 {
    static void demo() {
//        Person.metaClass.invokeMethod = {
//            name, args ->
//                System.out.println "metaclass invokeMethod $name,$args"
//
//        }

        new Person4().develop()
        new Person4().develop1123()
        new Person4().salary1
        new Person4().walk()
    }

    static void main(String... args) {
        demo()
    }
}

/**
 * 1. implement methodMissing, propertyMissing;
 * 2. not implement methodMissing, propertyMissing;
 * 3. implement GroovyInterceptable;
 * 4. metaClass invoke;
 */
class Person4 {

    def salary
    Walk _walk = new Walk()

    def develop() {
        println 'develop'
    }

    // 2. comments off
    //方法丢失
    def methodMissing(String name, def args) {
        println "methodMissing $name,$args"
        return null
    }

    // 2. comments off
    //属性丢失
    def propertyMissing(String name) {
        println 'propertyMissing'
        return null
    }

    @Override
    Object invokeMethod(String name, Object args) {
//        System.out.println "invokeMethod $name,$args" // 4. comments off
        metaClass.invokeMethod(this, 'println', "invokeMethod $name,$args") // 4.

        this._walk.metaClass.invokeMethod(this._walk, 'walk', args)
        return null
    }
}

class Walk {
    def walk(who = 'michael') {
        println "${who} is walking"
    }

    @Override
    Object invokeMethod(String name, Object args) {
        metaClass.invokeMethod(this, name, args)
    }
}
