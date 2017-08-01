package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
//下面演示对字符串实现了操作符++的重载.++对应的方法名是next
for ( i='a';i<'d';i++)
    print i +", "

println ''

for( i in 'a'..'d')
    print i+", "

//下面演示的对于集合，重载了操作符<<,对应的方法名是leftShift()
lst = ['hello']
lst << 'there'
println lst

//下面演示对一个自定义类实现的+的重载。
class ComplexNumber{
    def real,imaginary

    def plus(other){
        new ComplexNumber(real:real+other.real,
                imaginary:imaginary+other.imaginary)
    }

    String toString(){
        "$real ${imaginary>0 ? '+':''}${imaginary}i"
    }
}

c1 = new ComplexNumber(real:1,imaginary:2)
c2 = new ComplexNumber(real:4,imaginary:-31)

println c1+c2
