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

        Person4 p = new Person4()

//        p.develop()
//        p.debug()
//        p.develop1123()
//        p.salary1
//        p.walk('panda')
//        p.fly('bird')
        println p.steps
        println p.high
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
    Fly _fly = new Fly()

    def develop() {
        println 'develop'
    }

    def debug() {
        println this.metaClass.methods
        println this.metaClass.methods.collect {it.name}
        println this.metaClass.metaMethods
        println this.metaClass.classNode
        println this.metaClass.methods[-1].getMopName()
        println this.metaClass.methods[-1].getSignature()
        println this.metaClass.methods[-1].getName()
        println this._walk.metaClass.methods
        println this._walk.metaClass.metaMethods
        println this._walk.metaClass.classNode
        println this._fly.metaClass.methods
        println this._fly.metaClass.metaMethods
        println this._fly.metaClass.classNode
    }

    // 2. comments off
    //方法丢失
//    def methodMissing(String name, def args) {
//        println "methodMissing $name,$args"
//        return null
//    }

    private Handler property_handler
    Handler get_property_handler() {
        if(property_handler == null) {
            property_handler = new PropertyHandler(target: this._walk)
            property_handler.setSuccessor(new PropertyHandler(target: this._fly))
        }
        return property_handler
    }

    // 2. comments off
    //属性丢失
    def propertyMissing(String name) {
        println 'propertyMissing'
//        def value = null
//        if( name in this._walk.metaClass.properties.collect {it.name} ) {
//            value = this._walk.@"$name"
//        }
//        if( name in this._fly.metaClass.properties.collect {it.name} ) {
//            value = this._fly.@"$name"
//        }
//        return value
        this.get_property_handler().handleRequest(name, null)
    }

    private Handler method_handler
    Handler get_method_handler() {
        if(method_handler == null) {
            method_handler = new MethodHandler(target: this._walk)
            method_handler.setSuccessor(new MethodHandler(target: this._fly))
        }
        return method_handler
    }


    @Override
    Object invokeMethod(String name, Object args) {
//        System.out.println "invokeMethod $name,$args" // 4. comments off
        metaClass.invokeMethod(this, 'println', "invokeMethod $name,$args") // 4.

//        if( name in this._walk.metaClass.methods.collect {it.name}) {
//            this._walk.metaClass.invokeMethod(this._walk, name, args)
//        }
//
//        if( name in this._fly.metaClass.methods.collect {it.name}) {
//            this._fly.metaClass.invokeMethod(this._fly, name, args)
//        }
        this.get_method_handler().handleRequest(name, args)

        return null
    }
}

abstract class Handler {
    protected Handler successor;
    Object target;
    public Handler setSuccessor(Handler suc) {
        this.successor = suc
        return this.successor
    }
    public void resetSuccessor() {
        this.successor = null;
    }
    public abstract Object handleRequest(String name, Object args);
}

class MethodHandler extends Handler {
    Object handleRequest(String name, Object args) {
        println this.target.metaClass.methods.collect {it.name}
        if( name in this.target.metaClass.methods.collect {it.name}) {
            this.target.metaClass.invokeMethod(this.target, name, args)
        } else {
            this.successor.handleRequest(name, args)
        }
    }
}

class PropertyHandler extends Handler {
    Object handleRequest(String name, Object args) {
        println this.target.metaClass.properties.collect {it.name}
        if( name in this.target.metaClass.properties.collect {it.name}) {
            this.target.@"$name"
        } else {
            this.successor.handleRequest(name, args)
        }
    }
}

class Walk {
    def steps = 100
    def walk(who = 'michael') {
        println "${who} is walking"
    }
}

class Fly {
    def high = 10
    def fly(who = 'michael') {
        println "${who} is flying"
    }
}