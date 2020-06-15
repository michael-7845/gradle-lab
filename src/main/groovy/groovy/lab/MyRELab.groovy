package groovy.lab

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyRELab {
    static void demo1() {
        /**
        //  定义正则表达式，里面的特殊字符会自动转义
        ~// 定义正则表达式，会将字符串编译成Pattern
                =~  将左边的字符串局部匹配右边的正则表达式，并返回Matcher
        ==~ 将左边的字符串全局匹配右边的正则表达式，并返回boolean
         **/

        //语法“//”定义正则表达式，对于出现在表达式中特殊字符，会自动进行转义
        def p1 = /a\d$@/;
        println p1;//  /a\d$@/
        println p1.class;//类型还是字符串
        println 'a\\d$@' == p1;//true

        // "//"中可以使用GString
        def sname = "name";
        println (/$sname/ == "$sname");//true
        println (/$sname/ == sname);//true

        println "============================";
        //~：用在字符串之前，会将字符串编译成Pattern
        def p2 = ~/groovy\d+/;
        println p2;// groovy\d+
        println p2.class; //java.util.regex.Pattern

        println "============================";

        //=~：将操作符左边的字符串跟右边的Pattern进行局部匹配，返回值为Matcher
        def matcher = ("hello groovy88 test" =~ p2);
        println matcher;//java.util.regex.Matcher[pattern=a region=0,3 lastmatch=]
        println matcher.class;//java.util.regex.Matcher
        println matcher[0] == "groovy88";//没有匹配到matcher[0]为null,所以这里在校验有没有匹配到时可以通过getCount()来判断
        println matcher.getCount();
        println "============================";

        //用法跟=~类似，只是这里进行的精确匹配，即左边的整个字符串跟左边的模式进行匹配，==~的结果跟Matcher.matches()的结果是一样的。返回值为Boolean
        def matcher2 = ("hello groovy88 test" =~ /groovy\d+/);//局部匹配返回Matcher
        def matcher3 = ("hello groovy88 test" ==~ /groovy\d+/);//全局匹配返回boolean

        println matcher2[0];//groovy88
        println matcher3;//false

        println "==========matches==================";
        //Matcher类的matches方法只有在全匹配才返回true
        def matcher4 = ("groovy123" =~ /groovy\d+/);
        println matcher4.matches();//true

        matcher4 = ("testgroovy123" =~ /groovy\d+/);
        println matcher4.matches();//false

        //println ("groovy123" =~ /groovy\d+/).matches();//这种写法还有错误
    }

    static void demo2() {
        def matcher = ("^([a-zA-Z0-9-]+)-approuter-caas2-sap-stage.cfapps.us10.hana.ondemand.com\$" =~ /.*(-app.*com)\$/)
        println matcher[0][1]

        def location = 'https://automation-test-approuter-caas2-sap-stage.cfapps.us10.hana.ondemand.com/order-export/jobs/997dcf8b-97e4-4dc5-946c-75f75fc35c3d/executions/8f11d285-2dec-4773-aebe-4236c30157a5'
        def executionId = (location =~ /.*executions\/(.*)$/)
        println executionId[0][1]
    }

    static void demo3() {
        def matcher = ("successMichaelYu@simulator.amazonses.com" =~ /(.*)@(.*)/)
        println matcher[0][1]

        def s = 'https://caasstagemy-approuter-caas2-sap-stage.cfapps.us10.hana.ondemand.com/order-export/jobs/c213b9a9-1d6a-43bf-b379-071b389ac17b'
        matcher = (s =~ /order-export\/jobs\/(.*)$/)
        println matcher[0][1]
    }

    static String extract(String s, Pattern p) {
        def matcher = (s =~ p)
        return matcher[0][1]
    }

    static void demo4() {
        println extract("successMichaelYu@simulator.amazonses.com", ~/(.*)@(.*)/)
    }

    // https://www.jianshu.com/p/f845f28b26e2
    static void demo5() {
        def p = ~/foo/
        assert p instanceof Pattern
        println p.class

        def text = "some text to match"
        def m = text =~ /.*(text).*(match)/
        assert m instanceof Matcher
        println m//java.util.regex.Matcher[pattern=a region=0,3 lastmatch=]
        println m.class;//java.util.regex.Matcher
        println m[0] == "match";//没有匹配到matcher[0]为null,所以这里在校验有没有匹配到时可以通过getCount()来判断
        println m.getCount();
        println m.find();
        println m[0]
        println m[0][0]
        println m[0][1]
        println m[0][2]
        println m.group()
        println m.group(0)
        println m.group(1)
        println m.group(2)
//        println m[1] // out of range -1..0
        if (!m) {
            throw new RuntimeException("Oops, text not found!")
        }

        m = text ==~ /match/
        assert m instanceof Boolean
        if (m) {
            throw new RuntimeException("Should not reach that point!")
        }
        println (text ==~ /.*(text).*(match)/)
    }

    static void demo6() {
        def p = ~/Order: (\d+). Order Line: (\d+) cannot be fulfilled./
        assert p instanceof Pattern
        println p.class

        def orderid_linenumbers = []

        println '-----------1. '
        def text = "Order: 7321746186. Order Line: 2 cannot be fulfilled."
        def text2 = "Order: 7321746186. Order Line: 3 cannot be fulfilled."
        def m = text =~ p
        println m[0]
        println m[0][1]
        println m[0][2]
        orderid_linenumbers << [id: m[0][1], number: m[0][2]]

        println '-----------2. '
        def m1 = text =~ /Order: (\d+). Order Line: (\d+) cannot be fulfilled./
        println m1[0]
        println m1[0][1]
        println m1[0][2]
        orderid_linenumbers << [id: m1[0][1], number: m1[0][2]]

        println '-----------3. '
        def m2 = text2 =~ /Order: (\d+). Order Line: (\d+) cannot be fulfilled./
        println m2[0]
        println m2[0][1]
        println m2[0][2]
        orderid_linenumbers << [id: m2[0][1], number: m2[0][2]]

        println orderid_linenumbers
        println new HashSet(orderid_linenumbers)
    }

    static void demo6_1() {
        def _exported = ['Order: 9903151591 Order Line: 1 has been exported in muc-1-2019-05-03 05:16:07.199602.csv',
                         'Order: 2969527671 Order Line: 1 has been exported in boston-1-2019-05-03 05:16:07.419131.csv']

        def id_b = '2969527671'
        def wh = ['boston-1', 'muc-1']
        println _exported.findAll { it =~ /Order: ${id_b} Order Line: 1 has been exported in ${wh[0]}-\d{4}-\d{2}-\d{2}\s+\d{2}:\d{2}:\d{2}\.\d{1,6}\.csv/ }
//        println _exported.findAll { it =~ /Order: 2969527671 Order Line: 1 has been exported in boston-1-\d{4}-\d{2}-\d{2}\s+\d{2}:\d{2}:\d{2}\.\d{1,6}\.csv/ }
    }

    static void demo7() {
        //这次直接当做脚本用
        def texts = '''
The Chinese premier described the world's second-largest economy as a butterfly struggling to emerge from a chrysalis.
He said this transformation was filled with promise but also great pain.
He repeatedly paid tribute to Communist Party leader Xi Jinping and said that under the sound leadership of the Party, the Chinese people had the courage and ingenuity to overcome all difficulties.'''

        //p开头的单词
        def startsWithP = /\b[pP]\w*\b/

        def wordsStartsWithP = texts =~ startsWithP
        println("p开头的单词")
        while (wordsStartsWithP.find()) {
            print("${wordsStartsWithP.group()} ")
        }
        println()

        //以y结尾的单词
        def endsWithY = /^.*y$/
        def words = ['happy', 'foolish', 'something', 'java','lucky']

        def results = words.findAll { it ==~ endsWithY }.join(',')
        println("y结尾的单词:$results")
    }

    static void demo8() {
//        def p = ~/5a/
//        println find('12345abcd', p)
        println find('12345abcd', /5a/)
        println find2('<form enctype="application/x-www-form-urlencoded" method="post" action="https://caasstageqa.authentication.us10.hana.ondemand.com/saml/SSO/alias/caasstageqa.aws-live">', 'action="https://.*/saml/SSO/alias/(.+?)"')[1]
        println find('#1234567890', /#(\d+)/)
        println find2('#1234567890', /#(\d+)/)
        println find2('#1234567890', /#(\d+)/)[0]
        println find2('#1234567890', /#(\d+)/)[1]
        println find2('SKU: 481314', /SKU:\s+(\d+)/)[1]
    }

    static String find(String s, String p) {
        def m = s =~ p
        if(m.find()) {
            return "${m.group()}"
        }
        return null
    }

    static List find2(String str, String pattern) {
        def m = str =~ pattern
        if(m.find()) {
            return m[0]
        }
        return null
    }

    static void demo9() {
//        def m = 'place.order calculate cost' =~ /(\w+).{1}(\w+)/
//        if(m.find()) {
//            println m[0]
//        }
        println 'place.order'.replaceAll(/\W/, ' ')
        println 'place order'.replaceAll(/\W/, ' ')
        println 'place-order'.replaceAll(/\W/, ' ')
        println 'place_order'.replaceAll(/(\W|_)/, ' ')
        println 'promotion'.replaceAll(/\W/, ' ')

        String s = 'com\\upscale\\e2e\\spec\\multibrowser\\WbPwaMultiBrowserGebSpec\\001-002-both workbench and pwa-workbench input username_wb_.html'
        String s2 = '001-002-both workbench and pwa-workbench input username[wb]'
        println s.replaceFirst(/_\w+_.html/, '_pwa_.html')
        println s
        println s2.replaceFirst(/\[\w+\]$/, '[pwa]')
        println s2

        println 'Co:lor:'.replaceAll(/:$/, '')
    }

    static void _main() {
        demo9()
    }

    static void main(String... args) {
        _main()
    }
}
