package groovy.dsl.mopdemo.blog1

class MyClass{
    def hello(){
        'invoked hello directly'
    }
    def invokeMethod(String name, Object args){
        return "unknown method $name(${args.join(', ')})"
    }
}
def mine= new MyClass()
assert mine.hello() == 'invoked hello directly'
assert mine.foo("Mark", 19) == 'unknown method foo(Mark, 19)'