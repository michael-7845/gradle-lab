package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class MyClass2 implements GroovyInterceptable{
    def hello(){
        'invoked hello() directly'
    }
    def invokeMethod(String name, Object args){
        "invoked method $name(${args.join(', ')})"
    }

    GroovyObject go = 1
    GroovyObjectSupport gos = 1
}

def mine = new MyClass2()
assert mine.hello() == 'invoked method hello()'
assert mine.foo('Mark',19) == 'invoked method foo(Mark, 19)'

assert mine.&hello() == 'invoked hello() directly'