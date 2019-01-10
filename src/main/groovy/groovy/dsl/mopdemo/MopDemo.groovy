package groovy.dsl.mopdemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * Url:  https://www.jianshu.com/p/b9e1eb791723
 */
class MopDemo {
    static void lab() {
        def str = 'hello'
        def targetMethod = str.metaClass.getMetaMethod('toUpperCase')
        println targetMethod.invoke(str)
    }

    static void lab2() {
        AnInterceptable2 o = new AnInterceptable2()
        println o.nonExistingMethod(1)
    }

    static void lab3() {
        ClassWithInvokeAndMissingMethod o = new ClassWithInvokeAndMissingMethod()
        println o.existingMethod()
        println o.nonExistingMethod()

        ClassWithInvokeAndMissingMethod2 o2 = new ClassWithInvokeAndMissingMethod2()
        println o2.existingMethod()
        println o2.nonExistingMethod()
    }

    static void lab4() {
        def foo = new Foo()

        String propertyName = 'bar'
        String methodName = 'func1'

        //访问属性
        println foo[propertyName]
        println foo."$propertyName"

        //访问方法
        println foo."$methodName"()
        println foo.invokeMethod(methodName,null)
    }

    static void main(String... args) {
        lab4()
    }
}

//    动态访问方法或属性——Groovy 的语法糖
class Foo{
    int bar
    def func1(){ 'func1' }
}

// 实现了 GroovyInterceptable 接口,复写 invokeMethod,那么所有的方法调用,都会被路由到 invokeMethod 中
class AnInterceptable2 implements GroovyInterceptable {
    def existingMethod() { 'AnInterceptable2.existingMethod()'}

    def invokeMethod(String name, args) { "intercepted - name: ${name}, args: ${args}" }
}

// 普通的 Groovy 类,并实现 invokeMethod 和 methodMissing 方法
class ClassWithInvokeAndMissingMethod2 implements GroovyInterceptable {
    def existingMethod() { 'existingMethod' }

//    def invokeMethod(String name, args) { 'invoke called' }

    def methodMissing(String name, args) { 'missing called' }
}