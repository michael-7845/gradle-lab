package groovy.dsl.mopdemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyMop { // implements GroovyInterceptable {
    def post(String url) { println "post(${url})" }
    def post_body(String url, def body) { println "post_body(${url}, ${body})" }
    def get(String url) { println "get(${url})" }
    def put(String url, Object... params) { println "put(${url}, ${params})" }
    def invokeMethod(String name, args) {
//        this.metaClass.invokeMethod(this, 'println', "name: ${name}, args: ${args}")
        def m = name =~ /(\w+)\s(.+)/
        if(m.find()) {
            String _act = m[0][1]
            String _url = m[0][2]
            if(args) {
                this.metaClass.invokeMethod(this, "${_act}_body", [_url, args, ].flatten())
            } else {
//                Object[] parameters = [] as Object[]
//                someMethod.invoke(anObject, parameters);
//                this.metaClass.invokeMethod(this, _act, [_url, *parameters]) // fail
                this.metaClass.invokeMethod(this, _act, [_url, ].flatten())
            }
//            this.metaClass.invokeMethod(this, 'println', "act: ${_act}, url: ${_url}")
//            switch(_act) {
//                case('post'):
//                    this.metaClass.invokeMethod(this, 'post', [_url, ] + args)
//                    break
//                case('get'):
//                    this.metaClass.invokeMethod(this, 'get', [_url, ])
//                    break
//            }
        }
//        this.metaClass.invokeMethod(this, name, args)
    }

    static void main(String... args) {
        MyMop m = new MyMop()
        String subtype = 'expensive'
        String id = 'abc'
//        m."post /resources/${subtype}"()
//        m."post /resources/${subtype}"([name:'michael', ])
//        m."get /resource/${id}"()

        m."put /resource/${id}"()
    }
}
