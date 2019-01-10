package groovy.dsl.example2

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class SortHelperLab {
    static List scores = [new Student(no:'123',name:'Tom',chinScore:90,mathScore:99,englScore:60,physScore:88,chemScore:96),
                   new Student(no:'124',name:'Mike',chinScore:88,mathScore:90,englScore:90,physScore:98,chemScore:87),
                   new Student(no:'125',name:'Alice',chinScore:100,mathScore:55,englScore:98,physScore:67,chemScore:56)]


    static void lab() {
        def sorter = new SortHelper(scores)

        sorter.sortByChinScore()
        scores.each{
            println it.name
        }

        sorter.sortByMathScore()
        scores.each{
            println it.name
        }
    }

    static void main(String... args) {
        lab()
    }
}
