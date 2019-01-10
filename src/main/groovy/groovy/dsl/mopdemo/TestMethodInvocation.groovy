package groovy.dsl.mopdemo

class TestMethodInvocation { //extends GroovyTestCase {
    void testInterceptedMethodCallonPOJO() {
        def val = new Integer(3)
        Integer.metaClass.toString = { -> 'intercepted' }

        assertEquals "intercepted", val.toString()
    }

    //实现了 GroovyInterceptable 接口,复写 invokeMethod,那么所有的方法调用,都会被路由到 invokeMethod 中
    void testInterceptableCalled() {
        def obj = new AnInterceptable()
        assertEquals 'intercepted', obj.existingMethod()
        assertEquals 'intercepted', obj.nonExistingMethod()
        assertEquals 'intercepted', obj.invokeMethod("existingMethod", null)
        assertEquals 'intercepted', obj.invokeMethod("nonExistingMethod", null)
    }

    void testInterceptedExistingMethodCalled() {
        //将原有的 ex2 方法覆盖了
        AGroovyObject.metaClass.existingMethod2 = { -> 'intercepted' }
        def obj = new AGroovyObject()
        assertEquals 'intercepted', obj.existingMethod2()
    }

    void testUnInterceptedExistingMethodCalled() {
        def obj = new AGroovyObject()
        assertEquals 'existingMethod', obj.existingMethod()
    }

    void testPropertyThatIsClosureCalled() {
        def obj = new AGroovyObject()
        assertEquals 'closure called', obj.closureProp()
    }

    void testMethodMissingCalledOnlyForNonExistent() {
        def obj = new ClassWithInvokeAndMissingMethod()
        assertEquals 'existingMethod', obj.existingMethod()
        assertEquals 'missing called', obj.nonExistingMethod()
        assertEquals 'invoke called', obj.invokeMethod("haha", null)

    }

    void testInvokeMethodCalledForOnlyNonExistent() {
        def obj = new ClassWithInvokeOnly()
        assertEquals 'existingMethod', obj.existingMethod()
        assertEquals 'invoke called', obj.nonExistingMethod()
    }

    void testClassWithMethodMissingOnly(){
        def obj = new ClassWithMethodMissingOnly()
        assertEquals 'existingMethod', obj.existingMethod()
        assertEquals 'missing called', obj.nonExistingMethod()
        assertEquals 'missing called', obj.invokeMethod("haha",null)
    }
    void testMethodFailsOnNonExistent() {
        def obj = new TestMethodInvocation()
        shouldFail(MissingMethodException) { obj.nonExistingMethod() }
    }
}

// 实现了 GroovyInterceptable 接口,复写 invokeMethod,那么所有的方法调用,都会被路由到 invokeMethod 中
class AnInterceptable implements GroovyInterceptable {
    def existingMethod() {}

    def invokeMethod(String name, args) { 'intercepted' }
}

// 普通的 Groovy 类
class AGroovyObject {
    def existingMethod() { 'existingMethod' }

    def existingMethod2() { 'existingMethod2' }
    def closureProp = { 'closure called' }
}

// 普通的 Groovy 类,并实现 invokeMethod 和 methodMissing 方法
class ClassWithInvokeAndMissingMethod {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }

    def methodMissing(String name, args) { 'missing called' }
}

// 普通的 Groovy 类,并实现 invokeMethod
class ClassWithInvokeOnly {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }
}

class ClassWithMethodMissingOnly {
    def existingMethod() { 'existingMethod' }

    def methodMissing(String name, args) { 'missing called' }
}