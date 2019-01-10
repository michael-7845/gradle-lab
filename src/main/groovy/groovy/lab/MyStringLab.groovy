package groovy.lab

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

    static void _main() {
        demo5()
    }

    static void main(String... args) {
//        demo2('a', 'b', 'c')
        _main()
    }
}
