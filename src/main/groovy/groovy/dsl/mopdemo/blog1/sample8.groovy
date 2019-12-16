package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
class A8{
    String text
}
def a1= new A8(text: 'aBCdefG')
assert a1.metaClass.adaptee.class == MetaClassImpl

A8.metaClass.inSameCase = {-> text.toUpperCase()}
//triggers conversion of MetaClass of A to ExpandoMetaClass
//then adds new instance method 'inUpperCase' to class
//A.metaClass {  }

//
def a2 = new A8(text:'hiJKLmnOp')
assert a2.metaClass.adaptee.class == ExpandoMetaClass
//MetaClass of A changed for instances created after conversion trigger only
assert a2.inSameCase() == 'HIJKLMNOP'

//new method not available
assert a1.metaClass.adaptee.class == MetaClassImpl
try{ println a1.inSameCase();}
catch(e){assert e in MissingMethodException}

A.metaClass.inLowerCase = {-> text.toLowerCase()}
assert a2.inLowerCase() == 'hijklmnop'

//replace the method definition with another
A.metaClass.inSameCase = {-> text.toLowerCase()}
assert a2.inSameCase() == 'hijklmnop'

//add static methods
A.metaClass.'static'.inSameCase = {it.toLowerCase()}
assert A.inSameCase('qRStuVwXyz') == 'qrstuvwxyz'