package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class A9{
}

A9.metaClass.character = 'Cat in the Hat'

def a1 = new A9()
assert a1.character == 'Cat in the Hat'

def ourProperties = Collections.synchronizedMap([:])
A9.metaClass.setType = {String value -> ourProperties["${delegate}Type"] = value }
A9.metaClass.getType = { -> ourProperties["${delegate}Type"]}
a1.type = 'Hatted Cat'
assert a1.type == 'Hatted Cat'

def a2 = new A9()
A9.metaClass.constructor = { -> new A()}
try{
    a2 = new A9()
}catch(Error e){
    assert e in StackOverflowError
}

A9.metaClass.constructor = {String s -> new A9(character :s)}
a2 = new A9("Thing One")

A9.metaClass.'changeCharacterToThingTwo'= {-> delegate.character = 'Thing Two' }
a2.character= 'Cat in the Hat'
a2.changeCharacterToThingTwo()
assert a2.character == 'Thing Two'

['Hatted Cat', 'Thing', 'Boy', 'Girl', 'Mother'].each{p->
    A9.metaClass."changeTypeTo${p}"= {-> delegate.type= p}
}
a2.changeTypeToBoy()
assert a2.type == 'Boy'

a2.'changeTypeToHatted Cat'()
assert a2.type == 'Hatted Cat'