package groovy.engine

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
abstract class MyScript extends Script {
    String name

    String greet() {
        "Hello, $name!"
    }
}

