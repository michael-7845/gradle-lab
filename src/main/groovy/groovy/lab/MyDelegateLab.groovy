package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyDelegateLab {
//    @Delegate
//    Outer o = new Outer()

    static void demo1() {
//        MyDelegateLab lab = new MyDelegateLab()
        Outer o = new Outer()
        o.show()
        o.change()
        o.show()
    }

    static void demo2() {
        // 调用
        def bernie = new Manager()
        bernie.analyze()
        bernie.work()
        bernie.writeReport()
    }

    static void _main() {
        demo1()
    }

    static void main(String... args) {
        _main()
    }
}

/////////////////////////////////////
class Outer {
    @Delegate
    Inner i = new Inner()

    def change() {
        i.s = 'cba'
        i.i = 321
    }
}

class Inner {
    String s = 'abc'
    Integer i = 123

    def show() {
        println '------ inner '
        println s
        println i
    }
}

/////////////////////////////////////
// 工人
class Worker {
    def work() { println '工人搬砖' } //

    def analyze() { println '工人分析数据...' }

    def writeReport() { println '工人写代码' }
}
// 专家
class Expert{
    def analyze(){ println "专家分析数据..."}
}

//委托
class Manager{
    @Delegate Expert expert = new Expert()
    @Delegate Worker worker = new Worker()
}
