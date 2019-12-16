package annotation.demo;

import java.lang.annotation.*;

//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE,ElementType.FIELD})
//@interface TestAnnotation {
//    String msg();
//}

@TestAnnotation(msg="my annotation")
class Test {
}

public class AccessDemo {
    static void demo1() {
        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
        if (hasAnnotation){
            TestAnnotation annotation = Test.class.getAnnotation(TestAnnotation.class);
            System.out.println(annotation.msg());
        }
    }

    static public void main(String... args) {
        demo1();
    }
}
