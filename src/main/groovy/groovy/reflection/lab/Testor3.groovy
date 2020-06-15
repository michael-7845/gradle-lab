package groovy.reflection.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class Testor3 {
    public a = 1
    def b = 2
    private String c = 'c'
    String _e // = 'y'
    static String _s = 'z'

    public def d(String x) { println "d(): $x" }
    private def e() { println "e(): ${_e}" }
    static def s() { println "s(): ${_s}" }
}