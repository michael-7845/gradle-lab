package groovy.dsl.mopdemo.blog1

class MyClass2 implements GroovyInterceptable{
    def hello(){
        'invoked hello() directly'
    }
    def invokeMethod(String name, Object args){
        "invoked method $name(${args.join(', ')})"
    }
}

def mine = new MyClass2()
assert mine.hello() == 'invoked method hello()'
assert mine.foo('Mark',19) == 'invoked method foo(Mark, 19)'

assert mine.&hello() == 'invoked hello() directly'