package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
def x=3
def y=4
println x+y
println x.plus(y)
println x instanceof Integer   //输出true

def a=2/3
//进行小数的取三位小数运算.
def b = a.setScale(3,BigDecimal.ROUND_HALF_UP)
assert b.toString() == '0.667'

//进行不等于的比较
println 4<=>3     //输出1
println 4 != 3

println 4**3      //4的3次方,等于4.power(3)
println 4/3       //输出1.3333333333,等于4.div(3)
println 8.intdiv(3)     //输出1
println 8%3          //取余,等于8.mod(3)
