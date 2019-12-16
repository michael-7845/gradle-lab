package groovy.dsl.mopdemo.blog1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 * URL: https://attis-wong-163-com.iteye.com/blog/1235880
 */
ExpandoMetaClass.enableGlobally()
//call 'enableGlobally' method before adding to supplied class
List.metaClass.sizeDoubled = {-> delegate.size() * 2 }
//add method to an interface
def list = [] << 1 << 2
assert list.sizeDoubled() == 4