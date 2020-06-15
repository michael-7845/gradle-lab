package groovy.engine

import groovy.util.Eval
import org.codehaus.groovy.control.CompilerConfiguration
import groovy.lang.GroovyClassLoader

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 *
 * https://www.groovy-lang.org/integrating.html
 */
class MyGroovyShellLab {
    static void demo1() {
        assert Eval.me('33*3') == 99
        assert Eval.me('"foo".toUpperCase()') == 'FOO'

        assert Eval.x(4, '2*x') == 8
        assert Eval.me('k', 4, '2*k') == 8
        assert Eval.xy(4, 5, 'x*y') == 20
        assert Eval.xyz(4, 5, 6, 'x*y+z') == 26
    }

    static void demo2() {
        def shell = new GroovyShell()
        def result = shell.evaluate '3*5'

        def result2 = shell.evaluate(new StringReader('3*5'))
        assert result == result2

        def script = shell.parse '3*5'
        assert script instanceof groovy.lang.Script
        assert script.run() == 15
    }

    static void demo3() {
        def sharedData = new Binding()
        def shell = new GroovyShell(sharedData)
        def now = new Date()
        sharedData.setProperty('text', 'I am shared data!')
        sharedData.setProperty('date', now)

        String result = shell.evaluate('"At $date, $text"')
        assert result == "At $now, I am shared data!"
    }

    // getProperty(...) it is also possible to write from the script into the binding
    static void demo4() {
        def sharedData = new Binding()
        def shell = new GroovyShell(sharedData)

        shell.evaluate('foo=123')

        assert sharedData.getProperty('foo') == 123
    }

    // It is important to understand that you need to use an undeclared variable
    // if you want to write into the binding.
    // Using def or an explicit type like in the example below would fail
    // because you would then create a local variable:
    static void demo5() {
        def sharedData = new Binding()
        def shell = new GroovyShell(sharedData)

        shell.evaluate('int foo=123')

        try {
            assert sharedData.getProperty('foo')
        } catch (MissingPropertyException e) {
            println "foo is defined as a local variable"
        }
    }

    // You must be very careful when using shared data in a multithreaded environment.
    // The Binding instance that you pass to GroovyShell is not thread safe, and shared by all scripts.
    // It is possible to work around the shared instance of Binding by leveraging the Script instance
    // which is returned by parse:
    static void demo6() {

        def shell = new GroovyShell()

        def b1 = new Binding(x:3)
        def b2 = new Binding(x:4)
        def script = shell.parse('x = 2*x')
        script.binding = b1
        script.run()
        script.binding = b2
        script.run()
        assert b1.getProperty('x') == 6
        assert b2.getProperty('x') == 8
        assert b1 != b2
    }

    // However, you must be aware that you are still sharing the same instance of a script.
    // So this technique cannot be used if you have two threads working on the same script.
    // In that case, you must make sure of creating two distinct script instances:
    static void demo7() {
        def shell = new GroovyShell()

        def b1 = new Binding(x:3)
        def b2 = new Binding(x:4)
        def script1 = shell.parse('x = 2*x')
        def script2 = shell.parse('x = 2*x')
        assert script1 != script2
        script1.binding = b1
        script2.binding = b2
        def t1 = Thread.start { script1.run() }
        def t2 = Thread.start { script2.run() }
        [t1,t2]*.join()
        assert b1.getProperty('x') == 6
        assert b2.getProperty('x') == 8
        assert b1 != b2
    }

    static void demo8() {
        def config = new CompilerConfiguration()
        config.scriptBaseClass = 'MyScript'
        def shell = new GroovyShell(MyGroovyShellLab.class.classLoader, new Binding(), config)
        def script = shell.parse('greet()')
        assert script instanceof MyScript
        script.setName('Michel')
        assert script.run() == 'Hello, Michel!'
    }

    static void demo9() {
        def gcl = new GroovyClassLoader()
        def clazz = gcl.parseClass('class Foo { void doIt() { println "ok" } }')
        assert clazz.name == 'Foo'
        def o = clazz.newInstance()
        o.doIt()
    }

    // A GroovyClassLoader keeps a reference of all the classes it created, so it is easy to create a memory leak.
    // In particular, if you execute the same script twice, if it is a String, then you obtain two distinct classes!
    static void demo10() {
        def gcl = new GroovyClassLoader()
        def clazz1 = gcl.parseClass('class Foo { }')
        def clazz2 = gcl.parseClass('class Foo { }')
        assert clazz1.name == 'Foo'
        assert clazz2.name == 'Foo'
        assert clazz1 != clazz2
    }

    // the reason is that a GroovyClassLoader doesn’t keep track of the source text.
    // If you want to have the same instance, then the source must be a file, like in this example:
    static void demo11() {
        def file = new File('C:/github/gradle-lab/src/main/groovy/groovy/engine/Foo.grovvy')
        def gcl = new GroovyClassLoader()
        def clazz1 = gcl.parseClass(file)
        def clazz2 = gcl.parseClass(new File(file.absolutePath))
        assert clazz1.name == 'Foo'
        assert clazz2.name == 'Foo'
        assert clazz1 == clazz2
    }

    static void demo12() {

    }

    static void _main() {
        demo11()
    }

    static void main(String... args) {
        _main()
    }
}

