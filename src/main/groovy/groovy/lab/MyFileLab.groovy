package groovy.lab

import groovy.io.FileType

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyFileLab {
    ////////////////////////////////////////////////
    // https://blog.csdn.net/Al_assad/article/details/78044515
    static void _read_file() {
        //读取文件,并在控制台逐行打印
        def lineCount = 0;
        File file = new File("mydir/test.txt");
        file.eachLine{
            line -> println("line ${lineCount++}: ${line}");
        }

        //获取文件所有内容
        def fileStr = file.text;
        println(fileStr);
    }

    static void _write_file() {
        def strOut = """Hello world!
Welcome to Groovy!
This is some output to the new file."""

        File fileOut = new File("mydir/out.txt")
        fileOut.withWriter('utf-8') {
            writer -> writer.writeLine(strOut)
        }
    }

    static void _delete_file() {
        File file = new File("./mydir/out.txt");
        file.delete();
    }

    static void _copy_file() {
        File src = new File("./mydir/test.txt");
        File dst = new File("./mydir/dest.txt");
        dst << src; // "./mydir/test.txt" in dst
    }

    static void _file_property() {
        File file = new File("mydir/test.txt");
        def fileSize = file.length();           //获取文件大小
//        def filepath = file.absolutePath();     //获取文件绝对路径
        def filepath = file.getAbsolutePath()
        def filepath2 = file.getCanonicalPath();    //获取文件路径
        def isFile = file.isFile();             //判断是否文件

        println fileSize
        println filepath
        println filepath2
        println isFile
    }

    static void _dir() {
        //创建目录
        File dir = new File("./mydir/testDir");
        dir.mkdir();

        //列举机器上的驱动器
        def rootFiles = new File("test").listRoots()
        rootFiles.each {
            file -> println file.getAbsolutePath()
        }
        //output: C:\
        //        D:\

        //列举指定目录下的所有文件
        def file = new File("C:/github/gradle-lab");
        file.eachFile {
            f -> println(f.getAbsolutePath());
        }
        file.eachDirRecurse {
            f -> println(f.getAbsolutePath());
        }
//        file.eachFIleResource() {
//            f -> println(f.getAbsolutePath());
//        }

    }

    ///////////////////////////////////////////////
    // https://blog.csdn.net/chenyulancn/article/details/65443468
    static void _read_file2() {
        //最简洁版本
        println new File("mydir/tmp.csv").text

        //读取每一行内容
        File file = new File('mydir/tmp.csv')
        assert file.name == 'tmp.csv'
        assert !file.isAbsolute()
        assert file.path == 'mydir\\tmp.csv'
        assert file.parent == 'mydir'

        //使用系统默认的编码处理文件流
        file.eachLine { println it }
        //指定处理流的编码
        file.eachLine("UTF-8") { println it }
        file.eachLine("UTF-8", 10) { str, no ->
            println str
            println no
        }

        //对文件中每一行的内容做处理
        file.splitEachLine("\t") { println it  }

        //以大写行式输出文件内容
        def lineList = file.readLines();
        lineList.each {
            println it.toUpperCase();
        }

        file.filterLine {String str->
            if (str.contains('code'))
                println str
        }.writeTo(new PrintWriter(System.out))
    }

    static def writeFile(fileName) {
        def file = new File(fileName)

        if (file.exists())
            file.delete()

        // 除了 file.newPrintWriter() 可以得到一个 PrintWriter，
        // 类似方法还有 file.newInputStream()、 file.newObjectInputStream()等
        def printWriter = file.newPrintWriter()

        printWriter.write('The first content of file')
        printWriter.write('\n')
        printWriter.write('The first content of file')

        printWriter.flush()
        printWriter.close()
    }

    static def writeFile2(fileName) {
        new File(fileName).withPrintWriter { printWriter ->
            printWriter.println('The first content of file')
        }
    }

    static void _write_file2() {
        writeFile('mydir/out2.txt')

        writeFile2('mydir/out3.txt')
    }

    static void _parse_xml() {
        def customers = new XmlSlurper().parse(new File("mydir/customers.xml"))
        /*对文件进行解析*/
        for(customer in customers.corporate.customer){
            println "${customer.@name} works for${customer.@company}";
        }
    }

    static void _parse_property() {
        def props = new Properties()
        new File("mydir/message.properties").withInputStream {
            stream -> props.load(stream)
        }

        // accessing the property from Properties object using Groovy's map notation
        println "capacity.created=" + props["capacity.created"]

        def config = new ConfigSlurper().parse(props)
        // accessing the property from ConfigSlurper object using GPath expression
        println "capacity.created=" + config.capacity.created


        config = new ConfigSlurper().parse(new File("mydir/message.properties").text)
//        message.properties 内容如下：
//        capacity {
//            created="x"
//            modified="y"
//        }
    }

    static void _dir2() {
        def dir = new File('C:/github/gradle-lab')
        if (dir.isDirectory()) {
            dir.eachFileRecurse { file ->
                println file
            }
        }

        dir.eachFileMatch(~/.*\.txt/) {File it-> println it.name  } //使正则表达式匹配文件名
        dir.eachFileMatch(FileType.DIRECTORIES, ~/.*\.txt/) { File it -> println it.name  }
    }

    static void demo1() {
        println new File('.').getCanonicalPath()
    }

    static void _main() {
//        demo1()
//        _read_file()
        _write_file()
//        _delete_file()
//        _copy_file()
//        _file_property()
//        _dir()

//        _read_file2()
        _write_file2()
//        _parse_xml()
//        _parse_property()
//        _dir2()
    }

    static void main(String... args) {
        _main()
    }
}
