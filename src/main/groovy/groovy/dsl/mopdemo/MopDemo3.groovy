package groovy.dsl.mopdemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://www.ibm.com/developerworks/cn/java/j-pg09205/#artrelatedtopics
 */
class MopDemo3 {
    static void demo() {
        def hndler = new MOPHandler()
        hndler.helloWorld()
        hndler.createUser("Joe", 18, new Date())
        hndler.name
    }

    static void main(String... args) {
        demo()
    }
}

class MOPHandler {

    def invokeMethod(String method, Object params) {
        println "MOPHandler was asked to invoke ${method}"
        if(params != null){
            params.each{ println "\twith parameter ${it}" }
        }
    }

    def getProperty(String property){
        println "MOPHandler was asked for property ${property}"
    }
}
