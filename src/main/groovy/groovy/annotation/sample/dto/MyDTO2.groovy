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
@FilePath(path='/json/')
class MyDTO2 {

    Map dtos

    public MyDTO2(Map params) {
        dtos = [:]
        if(MyDTO2.class.isAnnotationPresent(FilePath.class)) {
            println 'has annotation'
            Annotation a = MyDTO2.class.getAnnotation(FilePath.class)
            println a
            println this.getClass().getResource(a.path())
            println this.getClass().getResource(a.path()).toURI()
            File dir = new File(this.getClass().getResource(a.path()).toURI())
            println dir.list()
            dir.list().each { f ->
//                String file = new File(a.path(), f).getPath()
//                println "$f, ${file}"
//                println this.getClass().getResourceAsStream('/json/demo.json')
                String name = f.replaceFirst(/\.(\S)+$/, '')
                String source = String.join('/', [a.path().split(/\W/), f].flatten())
                        // String.join('/', ['', a.path().replaceAll(/\W/, ''), f])
                println "dtos.$name from $source"
//                dtos[(name)] = IOUtils.toString(this.getClass().getResourceAsStream(source), CharEncoding.UTF_8)
            }
        }
    }

    static lab1() {
//        String file = new File('demo.json')
//        println File.pathSeparator
//        println File.separator
//        println file
//        println file.toURI()
//        println file.toURI().toString()
//        println file.toURI().toURL()

        File myFile = new File('json', 'demo.json') //("/home/folder1/a.txt")
        println(myFile.toURI().toString())
        println this.getClass().getResourceAsStream(myFile.toURI().toString())

//        File dir = new File(this.getClass().getResource('/json/').toURI())
//        println this.getClass().getResourceAsStream('/json/demo.json')
    }

    static lab2() {
        def dto = new MyDTO2()
        println dto.dtos
    }

    static void main(String... args) {
        lab2()
    }
}
