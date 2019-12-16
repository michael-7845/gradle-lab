package groovy.dsl.mopdemo.blog1

import org.codehaus.groovy.runtime.InvokerHelper;

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
public class MyMetaClass7 extends DelegatingMetaClass{
    MyMetaClass7(Class theClass){
        super(theClass)
    }

    Object invokeConstructor(Object[] arguments){
        []
    }
}

class A7{}

def amc = new MyMetaClass7(A7)
amc.initialize()

InvokerHelper.metaRegistry.setMetaClass(A7,amc)

def a = new A7()
assert a.class == ArrayList
assert (a<<1<<2<<3).size() == 3