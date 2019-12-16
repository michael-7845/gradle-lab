package annotation.demo;

import java.lang.annotation.Annotation;

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
public class RepeatableDemo {
    static void demo1() {
        System.out.println(Person.class.isAnnotationPresent(Role.class));
        System.out.println(Person.class.isAnnotationPresent(Roles.class));

        Annotation[] annotations = Person.class.getAnnotations();
        System.out.println(annotations.length);
        System.out.println(annotations);
        Roles p1=(Roles) annotations[0]; // annotation - Roles, not Role
        for(Role t:p1.value()){
            System.out.println(t.role());
        }
    }

    static public void main(String... args) {
        demo1();
    }
}
