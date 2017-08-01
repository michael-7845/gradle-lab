package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
//List

assert [1,2,3,4] ==(1..4)
//集合是有顺序的！！
assert [1,2,3,4] !=[1,2,4,3]
assert [1,2,3]+[1] ==[1,2,3,1]
//将一个元素添加到已经存在的list中
assert [1,2,3]<<1 ==[1,2,3,1]
//从已有的list中删除元素
assert [1,2,3,1]-[1] ==[2,3]
assert [1,2,3]*2 ==[1,2,3,1,2,3]
//flatten:扁平的意思
assert [1,[2,3]].flatten() ==[1,2,3]
assert [1,2,3].reverse()==[3,2,1]
//测试两个集合是否有交叉
assert [1,2,3].disjoint([4,5,6])
//intersect:得到两个集合中的交集
assert [1,2,3].intersect([4,3,1]) ==[3,1]
//对集合进行循环处理
assert [1,2,3].collect{it+3} ==[4,5,6]
assert [1,2,3,1].unique().size()==3
assert [1,2,3,1].count(1) ==2
assert [1,2,3,4].min() ==1
assert [1,2,3,4].max() ==4
assert [1,2,3,4].sum() ==10
assert [4,2,1,3].sort() ==[1,2,3,4]
//对集合进行循环处理判断，并返回结果
assert [4,2,1,3].findAll{it%2==0} ==[4,2]

//对集合的迭代
def expected = [1,2,3,'test']
expected.each{test -> println test}


//Map

def map = [a:1,'b':2]
println map    //[a:1, b:2]
println map.a
println map['a']
println map.keySet()  //[a, b]

map = [:]
map[1] = 'a';map[2]='b'
map[true] = 'p'
map[false] = 'q'
map[null]='x';map['null'] = 'z'
assert map ==[1:'a',2:'b',(true):'p',(false):'q',(null):'x'
              ,'null':'z']

//下面的例子很有用，对map进行了很快速的循环输出！！
def sb = new StringBuffer();
[1:'a',2:'b',3:'c'].each{k,v->sb << "$k:$v, "}

//下面是输出字符串
[1:'a',2:'b',3:'c'].each{k,v-> println "$k:$v, "}

assert sb.toString() =='1:a, 2:b, 3:c, '

//下面又是一种快速的输出map为字符串形式的方法
map = [1:'a',2:'b',3:'c']
def string = map.collect{k,v -> "$k:$v"}.join(', ')
assert string == '1:a, 2:b, 3:c'

//下面演示一个对city进行分类的例子
assert [
        [name:'C',city:'London'],
        [name:'S',city:'London'],
        [name:'M',city:'LA'],
        [name:'Z',city:'HK'],
        [name:'A',city:'HK']
].groupBy{it.city}==[
        London:[ [name:'C',city:'London'],
                 [name:'S',city:'London']],
        LA:[ [name:'M',city:'LA']],
        HK:[ [name:'Z',city:'HK'] ,
             [name:'A',city:'HK'] ]
]

//map中顺序也是有关系的！顺序不对的map也不相等
assert [ [name:1,city:'L'],[name:2,city:'H']] != [ [name:2,city:'H'],[name:1,city:'L']]
