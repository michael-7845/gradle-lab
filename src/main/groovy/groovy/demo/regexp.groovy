package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
//正则表达式：
assert 'Hello World!' =~ /Hello/
assert 'Hello World!' ==~ /Hello\b.*/
def p = ~/Hello\b.*/
assert p.class.name == 'java.util.regex.Pattern'

//下面的next方法是返回的下一个字符！
assert "1.23".replaceAll(/./){ch-> ch.next()}== "2/34"

assert "1.23".replaceAll(/\d/){num -> num.toInteger()+1}=='2.34'

//下面的 == 不可以换行！否则就是语法错误！！为什么？？因为每一行后面默认就是有一个分号的！！
assert "1.23".replaceAll(/\d+/){num ->
    num.toInteger()+1} == '2.24'