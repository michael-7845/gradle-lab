package groovy.annotation.type

import java.lang.annotation.Repeatable

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
@interface Persons {
    Person[]  value();
}


@Repeatable(Persons.class)
@interface Person{
    String role() default ""
}


@Person(role="artist")
@Person(role="coder")
@Person(role="PM")
public class SuperMan{

}
