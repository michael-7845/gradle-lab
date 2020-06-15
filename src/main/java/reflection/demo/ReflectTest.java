package reflection.demo;

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
//定义了个类，随便写了些属性，方法和构造方法
class ReflectTest {
    private String title;
    private int num;
    public String content;
    protected double income;

    public ReflectTest() {
    }

    private ReflectTest(String title, int num, double income) {
        this.title = title;
        this.num = num;
        this.income = income;
    }

    public ReflectTest(String title, int num) {
        this.title = title;
        this.num = num;
    }

    private void getSomeThing() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
