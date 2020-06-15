package groovy.annotation.type

import java.lang.annotation.Annotation
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    public int id() default -1
    public String msg() default "Hi"
}

@Deprecated
class Hero {
    def say() {}
    def speak() {}
}

@TestAnnotation(id=3,msg="hello annotation")
public class Test {

    @Check(value="hi")
    Integer a

    @Perform
    public void testMethod(){}

    @SuppressWarnings("deprecation")
    public void test1(){
        Hero hero = new Hero();
        hero.say();
        hero.speak();
    }

    static lab3() {
        if(Test.class.isAnnotationPresent(TestAnnotation.class)) {

        }

    }

    static lab2() {
        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);

        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
            //获取类的注解
            System.out.println("id2:"+testAnnotation.id());
            System.out.println("msg2:"+testAnnotation.msg());
        }

        try {
//            System.out.println("try");
            Field a = Test.class.getDeclaredField("a");
            a.setAccessible(true);
//            System.out.println("${a}");
            //获取一个成员变量上的注解
            Check check = a.getAnnotation(Check.class);
//            System.out.println("${check}");
//            System.out.println("${a.getAnnotations()}");

            if ( check != null ) {
                System.out.println("check value:"+check.value());
            }

            Method testMethod = Test.class.getDeclaredMethod("testMethod");
//            testMethod.setAccessible(true)

            if ( testMethod != null ) {
                // 获取方法中的注解
                Annotation[] ans = testMethod.getAnnotations();
                for( int i = 0;i < ans.length;i++) {
                    System.out.println("method testMethod annotation:"+ans[i].annotationType().getSimpleName());
                }
            }
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static lab1() {
        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);

        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);

            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }
    }

    public static void main(String[] args) {
        lab2()
    }
}