package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class MyClass3{
    def greeting = 'accessed greeting directly'
    Object getProperty(String property){
        "read from property $property"
    }

    void setProperty(String property, Object newVlaue){
        throw new Exception("wrote to property $property")
    }
}
def mine = new MyClass3()
assert mine.greeting == 'read from property greeting'
try{
    mine.greeting = 'hi'
}catch(e){
    assert e.message == 'wrote to property greeting'
}

assert mine.@greeting == 'accessed greeting directly'