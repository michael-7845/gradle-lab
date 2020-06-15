package groovy.annotation.sample.dto

import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.CharEncoding

import java.lang.annotation.Annotation
import java.lang.reflect.Field

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyDTO {
    @FilePath(path='/json/TestJson2.json')
    String dto

    public MyDTO(Map params) {
        Field f = MyDTO.class.getDeclaredField('dto')
        if(f.isAnnotationPresent(FilePath.class)) {
            Annotation a = f.getAnnotation(FilePath.class)
            dto = IOUtils.toString(this.getClass().getResourceAsStream(a.path()), CharEncoding.UTF_8)
        }
    }

    static void main(String... args) {
        def dto = new MyDTO()
        println dto.dto
    }
}
