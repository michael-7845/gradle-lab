package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
//闭包的一个例子：

import static java.lang.Math.*

//闭包例子1
piA = { 22/7 }
piB = { 333/106 }
piC = { 355/113 }
piD = { 0.6*(3+sqrt(5))}
piE = { 22/7 +37/47 +888/83}
piF = { sqrt(sqrt(2143/22)) }

//这里就是闭包的实际算法部分。
howCloseToPI = { abs(it.value() - PI) }

algorithms = ['piA':piA,'piB':piB,'piC':piC,'piD':piD,'piE':piE,'piF':piF]

findBestPI(algorithms)

def findBestPI(map){
    map.entrySet().sort(howCloseToPI).each{
        entry ->//下面调用了闭包的函数
            def diff = howCloseToPI(entry)
            println "Alorithm $entry.key differs by $diff"
    }
}

doubleNum = {num ->num*2}
println doubleNum(3)

//闭包例子2
//下面的方法定义就是使用了闭包。传入了num，closure，其中closure是函数类型。
processThenPrint = {num,closure->
    num = closure(num);println "num is $num"
}
//下面传入了函数名作为第二个参数
processThenPrint(3,doubleNum)
//下面传入第二个参数是匿名函数
processThenPrint(10){it/2}

//闭包例子3
//下面又是一个闭包的例子
def houston(Closure  doit){
    (10..1).each{
        count -> doit(count)
    }
}

houston{ println it}

//闭包例子4,用于迭代
3.times{println 'Hi'}

[0,1,2].each{number -> println number}

//注意下面的it！！
[1,3,5].each{ println it}

def println = {println it}
[4,5,6].each println

map = ['a':1,'b':2]
map.each{key,value -> map[key]=value*2 }
assert map == ['a':2,'b':4]

//注意下面的each后面是小括号，不是大括号了！
doubler = {key,value -> map[key] = value*2}
map.each(doubler)
assert map == ['a':4,'b':8]

def doubleMethod(entry){
    map[entry.key] = entry.value *2;
}
doubler = this.&doubleMethod
map.each(doubler)
assert map == ['a':8,'b':16]

//下面对集合的一些操作很容易的结合了闭包方法的使用
//查询子元素
assert [1,2,3].grep{it<3} ==[1,2]
//判断是否有一个满足条件
assert [1,2,3].any{ it%2==0 }
//判断是否全部满足条件
assert [1,2,3].every{ it <4}
//将集合中全部元素join
assert (1..9).collect{it}.join() == '123456789'
assert (1..4).collect{it*2}.join() == '2468'

//闭包例子5：新的函数curry！以及演示一个通过闭包将list进行修改的例子！！
def add = {x,y -> x+y}
def mult = {x,y -> x*y}
assert add(1,3) == 4
assert mult(1,3) == 3

def min = {x,y -> [x,y].min()}
def max = {x,y -> [x,y].max()}
def sub = {x,y -> return 'x - y = '+(x-y)}

//注意下面的curry函数，意思是默认的传入参数到原有的函数的第一个位置，并将结果返回为一个新的函数
//例如下面的triple就是得到一个数值的三倍！！
def triple = mult.curry(3);assert triple(2) ==6
def atLeastTen = max.curry(10)
def subTen = sub.curry(10)
assert atLeastTen(5)==10
assert atLeastTen(15) == 15
assert subTen(13) == 'x - y = -3'

//
def pairWise(list ,invoke){
    if(list.size()<2) return []
    def next = invoke(list[0],list[1])
    return [next]+pairWise(list[1..-1],invoke)
}

assert pairWise(1..5,add) == [3,5,7,9]
assert pairWise(1..5,mult) == [2,6,12,20]
assert pairWise(1..5,min) == [1,2,3,4]
assert pairWise(1..5,max) == [2,3,4,5]

//inject--参数是初始值，然后放在一个闭包里面对list的每个元素进行迭代操作！
assert 'cbaxabc' == ['a','b','c'].inject('x'){
    result,item -> item +result+item
}