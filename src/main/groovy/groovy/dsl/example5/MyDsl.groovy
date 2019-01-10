package groovy.dsl.example5

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyDsl {
    List<Client> clients = [new Client(name: 'michael'), new Client(name: 'mooncake')]

    static void make(closure) {
        MyDsl dsl = new MyDsl()
        closure.delegate = dsl
        closure()
    }

    static void main(String... args) {
        MyDsl.make {
            clients[0].show_name()
            clients[1].show_name()
        }
    }
}

class Client {
    String name


    String show_name() {
        println this.name
        return this.name
    }
}