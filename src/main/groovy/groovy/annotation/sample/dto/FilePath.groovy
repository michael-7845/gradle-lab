package groovy.annotation.sample.dto

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
@Retention(RetentionPolicy.RUNTIME)
@interface FilePath {
    String path() default '/json/TestJson.json'
}