package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
def firstname='Kate'
def surname='Bush'
//打印字符串的连接
println firstname*2

def fullname="$firstname $surname"
println fullname
println fullname-firstname
//凑足15个字符,不够就在左边补充空格
println fullname.padLeft(15)

//关于字符串的截取
println '[n..m]'
println fullname[0..3]
println fullname[-4..-1]
println fullname[0..-3]
//下面的方式比较特别,取第5个字符,以及3,2,1位置字符连接起来
assert fullname[5,3..1]=='Beta'

ff='aaaabbccadvbasd'
lines=ff.split('a')
//下面assert在后面表达式为false情况下抛出异常
assert lines.size()==7

//注意字符串中的特殊字符
def plain='\n\r\t\b\\\f\$'
assert plain.size()==7

//关于字符串的闭包(注意下面的括号的使用方法!)
fullname="${-> firstname} $surname"
println fullname
firstname = 'li'
surname='shuiqing'
println fullname

//关于字符串的运算符
fullname = fullname -'qing'-'l'+' hello world'
assert fullname=='i Bush hello world'

//字符串替换
def string = 'hippopotamus'
assert string.replace('ppopotam','bisc') == 'hibiscus'

//字符串反转,转换为list
assert'apple'.toList() == ['a', 'p', 'p', 'l', 'e']

//下面把字符串里面去掉重复数字,并排序.
string = "an apple a day"
assert string.toList().unique().sort().join() == ' adelnpy'

//下面方法取出得到字符串里面的各个单词,并进行反转
string = 'Yoda said, "can you see this?"'
revwords= string.split(' ').toList().reverse().join(' ')
assert revwords== 'this?" see you "can said, Yoda'
println revwords

//对数组进行筛选,找出反转之后还等于原来字符串并且长度大于5的字符串
words = ['bob', 'alpha', 'rotator', 'omega', 'reviver']
bigPalindromes= words.findAll{w-> w == w.reverse() && w.size() > 5}
assert bigPalindromes== ['rotator', 'reviver']
println bigPalindromes

//GString
String name = 'count'
int value1 = 23
int value2 = 22
GString s = "The value of $name is ${value1+value2}"
println s
String my_name = "michael"
GString full_name = "${my_name} yu"
println full_name
