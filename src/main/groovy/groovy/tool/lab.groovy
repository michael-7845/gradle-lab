println 'michael yu groovy script'

Binding binding = new Binding()
this.args.each {println it}

def sayHello(String name) {
    println "Hello, " + name
    "sayHello() return value: ${name}"
}

sayHello(args.toString())

[code: 0]