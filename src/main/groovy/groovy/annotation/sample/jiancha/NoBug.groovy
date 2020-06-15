package groovy.annotation.sample.jiancha

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
public class NoBug {

    @Jiancha
    public void suanShu(){
        println "1234567890"
    }
    @Jiancha
    public void jiafa(){
        println("1+1="+1+1)
    }
    @Jiancha
    public void jiefa(){
        println("1-1="+(1-1))
    }
    @Jiancha
    public void chengfa(){
        println("3 x 5="+ 3*5)
    }
    @Jiancha
    public void chufa(){
        println("6 / 0="+ 6 / 0)
    }

    public void ziwojieshao(){
        println("no bug in my program!")
    }

}
