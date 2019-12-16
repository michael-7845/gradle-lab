package groovy.lab

import static java.nio.charset.StandardCharsets.UTF_8

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyStringLab {
    static void demo1(String... args) {
        println args
    }

    static void demo2(String[] args) {
        demo1(args)
    }

    static void demo3() {
        String a = 'a'
        def s1 = "${a}_1"
        def s2 = a+"_1"
        println s1.getClass()
        println s1.toString().getClass()
        println s2.getClass()
    }

    static String ulr_join(String url, String path) {
        String _url = (url.endsWith('/')) ? url[0..-2] : url
        String _path = (path.startsWith('/')) ? path : '/'+path
//        if(url.endsWith('/')) {
//            _url =
//        }
//        if(! path.startsWith('/')) {
//            _path = "/${path}"
//        }
        _url+_path
    }

    static void demo4() {
        String url1 = 'http://baidu.com'
        String url2 = 'http://baidu.com/'
        String path1 = '/map?a=301&c=442'
        String path2 = 'map?a=301&c=442'
        println ulr_join(url1, path1)
        println ulr_join(url1, path2)
        println ulr_join(url2, path1)
        println ulr_join(url2, path2)
    }

    static void demo5() {
        String s1 = "testing"
        def gs1 = "${s1}-passed"
        def s2 = (gs1)
        def s3 = gs1.toString()
        println gs1.getClass()
        println s2.getClass()
        println s3.getClass()

        String s4 = "field=o13&page..."
        String s5 = s4.replaceAll('o13', '123456')
        println s5
    }

    static void demo6() {
        String s1 = 'abc1234'
        println s1.contains('bc')
        println 'c12' in s1
    }

    static void demo7() {
        String s1 = '962'
        String s2 = '8713'
        println s1 > s2

        println 962.toString() > 8713.toString()
    }

    static void demo8() {
//        def message = 'Groovy rocks!'
//
//// Get bytes array for String using UTF8.
//        def messageBytes = message.getBytes(UTF_8)
//
//// Encode using Base64 URL and Filename encoding.
//        def messageBase64Url = messageBytes.encodeBase64Url().toString()
//
//// Encode using Base64 URL and Filename encoding with padding.
//        def messageBase64UrlPad = messageBytes.encodeBase64Url(true).toString()
//
//        println messageBase64Url.getClass()
//        assert messageBase64Url == 'R3Jvb3Z5IHJvY2tzIQ'
//        assert messageBase64UrlPad == 'R3Jvb3Z5IHJvY2tzIQ=='
//
//// Decode the String values.
//        assert new String(messageBase64Url.decodeBase64Url()) == 'Groovy rocks!'
//        assert new String(messageBase64UrlPad.decodeBase64Url()) == 'Groovy rocks!'

        def text = "Going to convert this to Base64 encoding!"
        // Encode
        def encoded = text.bytes.encodeBase64().toString()
        println encoded
        // Decode
        byte[] decoded = encoded.decodeBase64()
        println new String(decoded)

        def s1 = 'xyz'
        println s1.bytes.encodeBase64().toString()
        println s1.bytes.encodeBase64().toString().decodeBase64()
    }

    static def demo9() {
        String s = '0123456789'
        println s[1..-1]
        println 1..-1
    }

    static def demo10() {
        List<String> s = ['broker.merchant', 'broker#merchant', 'broker merchant',
                    'broker-merchant', 'broker_merchant']
        s.each {
            println '------ '+it
            println it.replaceFirst(/[\W|_]merchant$/, '')
        }
    }

    static private String user_type(String name, Object args) {
        String _name = name.toLowerCase()
        String _arg = (args) ? ((String)args[0]).toLowerCase() : ''

        String _type
        if(_name.endsWith('merchant')) {
            _type = 'merchant'
        } else if (_name.endsWith('consumer')) {
            _type = 'user'
        } else if (_name.endsWith('customer')) {
            _type = 'user'
        } else if (_name.endsWith('anonymous')) {
            _type = 'anonymous'
        } else { // default is merchant
            _type = 'merchant'
        }
        // priority _arg > _name
        switch(_arg) {
            case('merchant'):
                _type = 'merchant'
                break
            case(['consumer', 'customer']):
                _type = 'user'
                break
            case('anonymous'):
                _type = 'anonymous'
                break
        }
        _type
    }

    static def demo11() {
        println '---------------- 1'
        println user_type('order', [])
        println user_type('order', ['merchant'])
        println user_type('order', ['anonymous'])
        println user_type('order', ['consumer'])
        println user_type('order', ['customer'])
        println '---------------- 2'
        println user_type('order', [])
        println user_type('order merchant', [])
        println user_type('order anonymous', [])
        println user_type('order consumer', [])
        println user_type('order customer', [])
        println '---------------- 3'
        println user_type('order-merchant', [])
        println user_type('order_anonymous', [])
        println user_type('order.consumer', [])
        println user_type('order#customer', [])
        println '---------------- 4'
        println user_type('order merchant', ['anonymous'])
        println user_type('order anonymous', ['consumer'])
        println user_type('order consumer', ['merchant'])
        println user_type('order customer', ['merchant'])

        println 'order-merchant'.replaceAll(/\W|\s/, '_')
        println 'order_anonymous'.replaceAll(/\W|\s/, '_')
        println 'order.consumer'.replaceAll(/\W|\s/, '_')
        println 'order#customer'.replaceAll(/\W|\s/, '_')
    }

    static void _main() {
        demo11()
    }

    static void main(String... args) {
//        demo2('a', 'b', 'c')
        _main()
    }
}
