package groovy.annotation.type

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Check {
    public String value()
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Perform {}

class TestCheck {
    @Check('1')
    Integer a

    @Check(value='1')
    Integer b

    @Perform
    c() {}
}
