package groovy.lab

import java.math.MathContext
import java.math.RoundingMode

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

    /*
    public static final int ROUND_UP = 0;
    public static final int ROUND_DOWN = 1;
    public static final int ROUND_CEILING = 2;
    public static final int ROUND_FLOOR = 3;
    public static final int ROUND_HALF_UP = 4;
    public static final int ROUND_HALF_DOWN = 5;
    public static final int ROUND_HALF_EVEN = 6;
    public static final int ROUND_UNNECESSARY = 7;
     */
    static void demo2() {
        def a=1/3
        println 'ROUND_UP: '+a.setScale(2, BigDecimal.ROUND_UP)
        println 'ROUND_DOWN: '+a.setScale(2, BigDecimal.ROUND_DOWN)
        println 'ROUND_CEILING: '+a.setScale(2, BigDecimal.ROUND_CEILING)
        println 'ROUND_FLOOR: '+a.setScale(2, BigDecimal.ROUND_FLOOR)
        println 'ROUND_HALF_UP: '+a.setScale(2, BigDecimal.ROUND_HALF_UP)
        println 'ROUND_HALF_DOWN: '+a.setScale(2, BigDecimal.ROUND_HALF_DOWN)
        println 'ROUND_HALF_EVEN: '+a.setScale(2, BigDecimal.ROUND_HALF_EVEN)
        def b=1/2
        println 'ROUND_UNNECESSARY: '+b.setScale(2, BigDecimal.ROUND_UNNECESSARY)
    }

    static void demo3() {
        def a = 132.96923 //0.00003 //2.12467
        def b = a / 0.01
        def c = b.setScale(0, BigDecimal.ROUND_DOWN)
        println b
        println c
        def d = a - 0.01 * c
        println d
        println d.setScale(3, BigDecimal.ROUND_CEILING)

        println '---------------------'
        def divisor = 0.01
        println a.subtract(divisor.multiply(a.divide(divisor, 0, BigDecimal.ROUND_DOWN)))
                .setScale(3, BigDecimal.ROUND_CEILING)

        println new BigDecimal(a - 0.01 * new BigDecimal(a / 0.01).setScale(0, BigDecimal.ROUND_DOWN))
                .setScale(3, BigDecimal.ROUND_CEILING)
    }

    static void demo4() {
//        divideAndRemainder(BigDecimal divisor)[0] 是商，
//        divideAndRemainder(BigDecimal divisor)[1]是余数。
//        MathContext(int setPrecision)
//        MathContext(int setPrecision, RoundingMode setRoundingMode)
//        MathContext(String val)
//        BigDecimal value1=new BigDecimal(1000,new MathContext(6));
//        BigDecimal divisor=new BigDecimal(0.01,new MathContext(6));
//        BigDecimal result=null;
//        result=value1.divideAndRemainder(divisor)[1];
//        def a = new BigDecimal(132.96923,new MathContext(6))
//        def divisor = new BigDecimal(0.01,new MathContext(6))
//        println a.divideAndRemainder(divisor) //, new MathContext(3, RoundingMode.UP))

        def a = 132.96923
        def divisor = 0.01
        def result, remainder
        println a.divideAndRemainder(divisor)[1].setScale(3, BigDecimal.ROUND_UP)
    }

    static void demo5() {
        def shfee = 10.00
        def line_size = 3.00
        println shfee.divide(line_size, 4, BigDecimal.ROUND_UP)

        def deviation = 0.004
        println deviation.multiply(2).setScale(2, BigDecimal.ROUND_UP)
    }

    static void _main() {

    }

    static void main(String... args) {
        demo5()
    }
}
