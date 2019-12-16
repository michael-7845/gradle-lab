package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class MyClass4 implements GroovyInterceptable{
    def greeting = 'accessed greeting'
    def id ='White: '

    Object getProperty(String property){
        try{
            return this.@id + //access field directly
                    'indirectly ' +
                    this.@"$property"
        }catch(e){
            return "no such property $property"
        }
    }

    def hello(Object[] args){"invoked hello with (${args.join(', ')})"}

    def id(){'Green: '}

    def invokeMethod(String name, Object args){
        try{
            return this.&id() + //call method directly
                    'indirectly ' +
                    this.&"$name"(args)
        }catch(e){
            return "no such method $name"
        }
    }
}

def mine = new MyClass4()
assert mine.greeting == 'White: indirectly accessed greeting'
assert mine.farewell == 'no such property farewell'

assert mine.hello(1, 'b', 3) == 'Green: indirectly invoked hello with (1, b, 3)'
assert mine.foo('Mark', 19) == 'no such method foo'