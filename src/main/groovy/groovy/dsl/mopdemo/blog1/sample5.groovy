package groovy.dsl.mopdemo.blog1

import org.codehaus.groovy.runtime.InvokerHelper

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
public class MyMetaClass extends DelegatingMetaClass{
    MyMetaClass(Class thisClass){
        super(thisClass)
    }

    Object invokeMethod(Object object, String methodName, Object[] arguments){
        "MyMetaClass: ${super.invokeMethod(object, methodName, arguments)}"
    }
}

class A{
    def bark(){'A: invoked bark()'}
    def invokeMethod(String name, Object args){
        "A: missing $name(${args.join(', ')})"
    }
}

def amc = new MyMetaClass(A)
amc.initialize()
def a = new A()
a.metaClass = amc

// example6
//InvokerHelper.instance.metaRegistry.setMetaClass(A, amc)

assert a.bark() == 'MyMetaClass: A: invoked bark()'

Thread.start {
    assert a.bark() == 'MyMetaClass: A: invoked bark()'
}

assert new A().bark() == 'A: invoked bark()'

assert a.bleet() == 'A: missing bleet()'