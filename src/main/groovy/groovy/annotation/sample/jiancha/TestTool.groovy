package groovy.annotation.sample.jiancha

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class TestTool {
    static groovy_version() {
        NoBug testobj = new NoBug()
        Method[] method = testobj.class.getDeclaredMethods()
        StringBuilder log = new StringBuilder()
        int errornum = 0

//        try { testobj.chufa() }
//        catch (Throwable e) {
//            println e.class
//            println e.message
//        }

        for ( Method m: method ) {
            if ( m.isAnnotationPresent( Jiancha.class )) {
                try {
                    m.setAccessible(true)
                    println "----- ${m.name}()"
                    testobj.&"${m.name}"()
//                    m.invoke(testobj, null);
                } catch (Exception e) {
                    println e.class
                    println e.message
                    println e.getCause()
//                    e.printStackTrace()
                    errornum++
                    log.append(m.getName())
                    log.append(" ")
                    log.append("has error:")
                    log.append("\n\r  caused by ")
//                    log.append(e.getCause().getClass().getSimpleName())
                    log.append(e.class.getSimpleName())
                    log.append("\n\r")
                    log.append(e.message)
                    log.append("\n\r")
                }
            }
        }

        log.append(testobj.class.getSimpleName())
        log.append(" has  ")
        log.append(errornum)
        log.append(" error.")

        println log.toString()
    }

    static void java_version() {
        // TODO Auto-generated method stub
        NoBug testobj = new NoBug();

        Class clazz = testobj.getClass();

        Method[] method = clazz.getDeclaredMethods();
        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errornum = 0;

        for ( Method m: method ) {
            // 只有被 @Jiecha 标注过的方法才进行测试
            if ( m.isAnnotationPresent( Jiancha.class )) {
                try {
                    m.setAccessible(true);
                    m.invoke(testobj, null);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }

        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());

    }

    public static void main(String[] args) {
        groovy_version()
//        java_version()
    }

}
