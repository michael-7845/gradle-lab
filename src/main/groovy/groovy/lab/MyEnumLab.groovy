package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyEnumLab {
    static void demo1() {
        //  直接获得枚举值
        def monday = WeekDay.Monday

        //  通过名字获得枚举
        monday = 'Monday' as WeekDay
        assert monday == WeekDay.Monday

        //  调用枚举中的方法
        assert WeekDay.Tuesday.isWorkingDay()
        println(WeekDay.Thursday.abbr)

        //  获得一个枚举值的索引
        println(monday.ordinal())

        //  通过索引获得枚举值
        println(WeekDay.values()[0])
    }

    static void demo2() {
        println MyEnum.Spring.toString()
    }


    static void _main() {
        demo2()
    }

    static void main(String... args) {
        _main()
    }
}

enum WeekDay {
    Monday('Mon'), Tuesday('Tue'), Wednesday('Wed'), Thursday('Thu'),
    Friday('Fri'), Saturday('Sat'), Sunday('Sun')

    def abbr

    WeekDay(abbr) {
        this.abbr = abbr
    }

    def isWorkingDay() {
        !(this == Saturday || this == Sunday)
    }
}

enum MyEnum {
    Spring('spring'), Summer('summer season'), Autumn('season autumn'), Winter('WINTER')

    String alias

    MyEnum(alias) {
        this.alias = alias
    }

    String toString() {
        this.alias
    }
}
