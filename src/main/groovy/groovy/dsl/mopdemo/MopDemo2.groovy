package groovy.dsl.mopdemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://blog.csdn.net/a568478312/article/details/79912881
 */
class MopDemo2 {
    static void demo() {
        Person.metaClass.invokeMethod = {
            name, args ->
                System.out.println "metaclass invokeMethod $name,$args"

        }

        new Person().develop()
        new Person().develop1123()
        new Person().salary1
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
class Person implements GroovyInterceptable {

    def salary

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
        return null
    }

}
