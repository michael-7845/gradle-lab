package groovy.reflection.lab

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 *
 * https://blog.csdn.net/hivon/article/details/4187302
 */
class MyReflectionLab {
    static void demo1() {
        def t = new Testor3()
        // java classic
        println t.getClass().getName()
        // groovy gpath
        println t.class.name
        println t.class.simpleName
    }

    static void demo2() {
        Map map = [:]
        println map.class
        // java.lang.NullPointerException: Cannot get property 'simpleName' on null object
//        println map.class.simpleName
        println map.getClass()
        println map.getClass().simpleName
        println map.getClass().name
    }

    static void demo3() {
        def t = new Testor3()
        def cls = t.class
        t._e = 'y'

        println '---------- java classic: access fields'
        println cls.fields
        println cls.declaredFields
        println cls.fields.collect { it.name }
        println cls.declaredFields.collect { it.name }
        println '---------- java classic: access field'
        Field f
        f = cls.getField('a')
        println f.get(t)
        f = cls.getDeclaredField('c') // access private
        f.setAccessible(true)
        println f.get(t)
        f = cls.getDeclaredField('_s') // access static
        f.setAccessible(true)
        println f.get(Testor3)
        println '---------- groovy gpath: access field'
        println t.a
        println t.b // groovy gpath, access private
        println t.c // groovy gpath, access private
        println t._e
        println Testor3._s
        println '---------- groovy string: access field'
        println t.'a'
        println t.'b'
        println t.'c'
        println t.'_e'
        println Testor3.'_s'
        println '---------- groovy @: access field'
        println t.@'a'
        println t.@'b'
        println t.@'c'
        println t.@'_e'
        println Testor3.@'_s'
        println '---------- java classic: access methods'
        println cls.methods
        println cls.declaredMethods
        println cls.methods.collect { it.name }
        println cls.declaredMethods.collect { it.name }
        println '---------- java classic: access method'
        Method m
        // member method
        m = cls.getDeclaredMethod('d', String.class)
        m.invoke(t, 'x')
        m = cls.getDeclaredMethod('e', null)
        m.setAccessible(true)
        m.invoke(t, null)
        // static method
        m = cls.getDeclaredMethod('s')
        m.invoke(cls)
        println '---------- groovy gpath: access method'
        t.d('x')
        t.e() // groovy gpath, access private
        Testor3.s()
        println '---------- groovy string: access method'
        t.'d'('x')
        t.'e'()
        Testor3.'s'()
        println '---------- groovy &: access method'
        t.&'d'('x')
        t.&'e'()
        Testor3.&'s'()
    }

    static void demo4() {

    }

    static void demo5() {

    }

    static void _main() {
        demo3()
    }

    static void main(String... args) {
        _main()
    }
}
