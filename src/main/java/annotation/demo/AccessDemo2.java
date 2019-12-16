package annotation.demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//@Retention(RetentionPolicy.RUNTIME)
//@interface TestAnnotation2 {
//    String msg();
//}

@TestAnnotation(msg="class annotation")
class Test2 {

    @TestAnnotation(msg="member annotation")
    private String msg;

    @TestAnnotation(msg="method annotation")
    private void setMsg(){

    }
}

public class AccessDemo2 {
    static void demo1() {
        boolean hasAnnotation = Test2.class.isAnnotationPresent(TestAnnotation.class);
        if (hasAnnotation) {
            TestAnnotation annotation = Test2.class.getAnnotation(TestAnnotation.class);
            if (annotation != null) {
                System.out.println(annotation.msg());
            }
        }

        try {
            //属性获取注解
            Field msg = Test2.class.getDeclaredField("msg");
            if (msg != null) {
                msg.setAccessible(true);
                TestAnnotation annotation = msg.getAnnotation(TestAnnotation.class);
                if (annotation != null) {
                    System.out.println(annotation.msg());
                }
            }
            //方法获取注解
            Method setMsg = Test2.class.getDeclaredMethod("setMsg");
            if (setMsg != null){
                TestAnnotation annotation = setMsg.getAnnotation(TestAnnotation.class);
                if (annotation != null) {
                    System.out.println(annotation.msg());
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static public void main(String... args) {
        demo1();
    }
}
