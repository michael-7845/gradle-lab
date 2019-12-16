package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class Bird{
    def name = 'Tweety'
    def twirp(){ 'i taught i saw a puddy cat' }
}

Bird.metaClass.invokeMethod = {name, args->
    def metaMethod = Bird.metaClass.getMetaMethod(name, args)
    metaMethod?metaMethod.invoke(delegate,args): 'no such method'
}

def a = new Bird()
assert a.twirp() == 'i taught i saw a puddy cat'
assert a.bleet() =='no such method'

Bird.metaClass.getProperty = {name->
    def metaProperty = Bird.metaClass.getMetaProperty(name)
    metaProperty?metaProperty.getProperty(delegate): 'no such property'
}
def b = new Bird()
assert b.name == 'Tweety'
assert b.filling == 'no such property'