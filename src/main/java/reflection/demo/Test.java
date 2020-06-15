package reflection.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 *
 * java中修饰符编号 ：
 * PUBLIC: 1   PRIVATE: 2   PROTECTED: 4    STATIC: 8   FINAL: 16
 * SYNCHRONIZED: 32   VOLATILE: 64   TRANSIENT: 128  NATIVE: 256
 * INTERFACE: 512   ABSTRACT: 1024   STRICT: 2048
 */
public class Test {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("reflect.ReflectTest");
            System.out.println("------获取类名-----------");
            System.out.println(clazz.toString());// 获取类的完整名
            System.out.println(clazz.getSimpleName());// 获取类名
            System.out.println("------获取类中的构造方法-----------");
            // 获得所有构造方法
            Constructor[] cons = clazz.getDeclaredConstructors();
            for (int i = 0; i < cons.length; i++) {
                System.out.println("所有构造方法之" + cons[i].getName());
                // 方法修饰符
                System.out.println("修饰符类型：" + cons[i].getModifiers());
            }
            // 获得所有公共构造方法
            Constructor[] cons1 = clazz.getConstructors();
            for (int i = 0; i < cons1.length; i++) {
                System.out.println("公共构造方法之" + cons1[i].getName() + "--");
                System.out.println("修饰符类型：" + cons1[i].getModifiers());
            }

            System.out.println("-----获取类中定义的方法------------");
            Method[] mths = clazz.getDeclaredMethods();
            for (int i = 0; i < mths.length; i++) {
                System.out.println("所有方法之" + mths[i].getName());
                System.out.println("修饰符类型：" + mths[i].getModifiers());
            }
            Method[] mths1 = clazz.getMethods();
            for (int i = 0; i < mths1.length; i++) {
                System.out.println("公有方法之" + mths1[i].getName());
                System.out.println("修饰符类型：" + mths1[i].getModifiers());
            }

            System.out.println("-------获取所有的属性------------");
            Field[] fls = clazz.getFields();
            for (int i = 0; i < fls.length; i++) {
                System.out.println("公有属性之" + fls[i].getName());
                System.out.println("修饰符类型：" + fls[i].getModifiers());
            }
            Field[] fls1 = clazz.getDeclaredFields();
            for (int i = 0; i < fls1.length; i++) {
                System.out.println("所有属性之" + fls1[i].getName());
                System.out.println("修饰符类型：" + fls1[i].getModifiers());
            }
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
