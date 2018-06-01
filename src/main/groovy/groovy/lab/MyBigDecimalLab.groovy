package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyBigDecimalLab {
    static void demo1() {
        BigDecimal b1 = new BigDecimal(9.500)
        print b1.divide(new BigDecimal(100)).toString()
    }

    static void demo2() {

    }

    static void _main() {

    }

    static void main(String... args) {
        demo1()
    }
}
