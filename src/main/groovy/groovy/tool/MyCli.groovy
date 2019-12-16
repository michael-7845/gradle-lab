package groovy.tool

import org.codehaus.groovy.control.CompilationFailedException

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class CliFlyweight implements iCli {
    MyCli my_cli
    String command

    CliFlyweight(Map params) {
        if (!('command' in params)) { throw new RuntimeException("no command!") }
        if (!('my_cli' in params)) { throw new RuntimeException("no my_cli!") }

        this.command = params.command ?: null
        this.my_cli = params.my_cli ?: null
    }

    @Override
    Map execute() {
        Process process = this.command.execute(my_cli.env, my_cli._parent_dir)
        def out = new StringBuffer()
        def err = new StringBuffer()
        process.consumeProcessOutput( out, err )
        process.waitFor()
//        if( out.size() > 0 ) println out
//        if( err.size() > 0 ) println err
        [out:out, err:err, text:out]
    }
}

class GroovyFlyweight implements iCli {
    MyCli my_cli
    String command

    GroovyFlyweight(Map params) {
        if (!('command' in params)) { throw new RuntimeException("no command!") }
        if (!('my_cli' in params)) { throw new RuntimeException("no my_cli!") }

        this.command = params.command ?: null
        this.my_cli = params.my_cli ?: null
    }

    @Override
    Map execute() {
        def cmds = this.command.split(/\s/)
        def script_name = cmds[0] // script
        def arguments = cmds - cmds[0] // argument

//        ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("Groovy");
        def binding = new Binding(args: arguments)
        GroovyScriptEngine engine = new GroovyScriptEngine([this.my_cli._parent_dir.toURI().toURL()] as URL[])
        StringWriter out_string = new StringWriter()
        StringWriter err_string = new StringWriter()
        PrintWriter out_writer = new PrintWriter(out_string)
        PrintWriter err_writer = new PrintWriter(err_string)
        binding.setProperty('out', out_writer)
        binding.setProperty('err', err_writer)

        def result = engine.run(script_name, binding)

        [out: out_string.toString(), err: err_string.toString(), text: out_string.toString(), result: result]
    }
}

class GroovyFlyweight2 implements iCli {
    MyCli my_cli
    String command

    GroovyFlyweight2(Map params) {
        if (!('command' in params)) { throw new RuntimeException("no command!") }
        if (!('my_cli' in params)) { throw new RuntimeException("no my_cli!") }

        this.command = params.command ?: null
        this.my_cli = params.my_cli ?: null
    }

    @Override
    Map execute() {
        def cmds = this.command.split(/\s/)
        def script_name = cmds[0]
        def arguments = cmds - cmds[0]

        Binding binding = new Binding(arguments as String[])
        GroovyShell groovy_shell = new GroovyShell(binding)
        def text = groovy_shell.evaluate(new File(this.my_cli._parent_dir, script_name));
        [text: text]
    }
}

class MyCli {
    File _parent_dir
    List<String> env
    Map<String, iCli> cli = [:]

    MyCli(Map params) {
//        if (!('parent_dir' in params)) { throw new RuntimeException("no parent_dir for my cli!") }
        def _dir_ = params?.parent_dir
        if (!((_dir_ instanceof String)||(_dir_ instanceof File)||(_dir_ == null))) {
            throw new RuntimeException("only support parent_dir type: String or File!")
        }
        def _dir = (_dir_ instanceof String) ? new File(_dir_) : _dir_
        this._parent_dir = _dir_ ? _dir : null
        this.env = params?.env ?: []
    }

    public MyCli with() { return this }
    public MyCli and() { return this }

    public MyCli system_env() {
        this.env = System.getenv().collect { k, v -> "$k=$v" }
        return this
    }

    public MyCli parent_dir(_dir) {
        if (!((_dir instanceof String)||(_dir instanceof File)||(_dir == null))) {
            throw new RuntimeException("only support dir type: String or File!")
        }
        this._parent_dir = (_dir instanceof String) ? new File(_dir) : _dir
        return this
    }

    Map execute(String _cmd, String flyweight_type = 'cli') {
        switch(flyweight_type.toLowerCase()) {
            case('cli'):
                if(!(_cmd in this.cli)) { cli[(_cmd)] = new CliFlyweight(command:_cmd, my_cli:this)}
                break
            case('groovy'):
                if(!(_cmd in this.cli)) { cli[(_cmd)] = new GroovyFlyweight(command:_cmd, my_cli:this)}
                break
        }
        cli[(_cmd)].execute()
    }

    static void lab() {
        MyCli mc = new MyCli()
        mc.with().parent_dir(new File('C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool'))
        println "mc: ${mc}"
        println mc.execute('cmd /c dir').text
        println mc.execute('python utc.py').text
        println mc.execute('python arguments.py').text
        println mc.execute('python arguments.py 1 2 3').text
        println mc.execute('python arguments.py 1 2 ').text
        println mc.execute('python arguments.py 1').text
        println mc.cli
    }

    static void lab_a() {
        MyCli mc = new MyCli(parent_dir:'C:/Users/i340951')
//        mc.with().system_env().and().with().parent_dir('C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool')
        mc.with().parent_dir(new File('C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool'))
        println "mc: ${mc}"
        println mc.execute('cmd /c dir').text
    }

    static void lab_b() {
        MyCli mc = new MyCli(parent_dir:'C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool')
        println "mc: ${mc}"

        def res = mc.execute('lab.groovy arg1 arg2 arg3', 'groovy')
        println res.out
        println res.err
        println res.result
//        println mc.execute('lab.groovy arg1 arg2 arg3').result // call from map
    }

    static void lab_c() {
        MyCli mc = new MyCli(parent_dir:'C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool')
        println "mc: ${mc}"

        println mc.execute('lab.groovy arg1 arg2 arg3', 'groovy').text
    }

    ////////////////////////////////////////////
    // demo and example

    //https://www.cnblogs.com/dreampursuer/p/5569266.html
    static void demo1() {
        println 'cmd /c dir'.execute(null, new File("C:\\tmp\\test")).text
    }

    static void demo2() {
        def proc = 'cmd /c dir'.execute()
//        proc.waitFor()
        proc.waitForOrKill(1000)
        println proc.text
    }

    static void demo3() {
        def proc = 'cmd /c dir'.execute()
//        InputStream in = proc.in
//        InputStream err = proc.err
//        OutputStream out = proc.out
        def inputStream = new InputStreamReader(proc.errorStream)
        BufferedReader bufferedReader = new BufferedReader(inputStream)
        while (true){
            String s = bufferedReader.readLine()
            if (s == null){
                break
            }
        }
        proc.waitFor()
        println proc.text
    }

    static void demo4() {
        def proc = 'cmd /c dir'.execute()
        def outputBuffer = new StringBuffer()
        def errorBuffer = new StringBuffer()
//        proc.consumeProcessOutput(outputBuffer, errorBuffer)
        proc.consumeProcessErrorStream(errorBuffer)
        proc.waitFor()
        println proc.text
    }

    static void lab1() {
        demo4()
    }

    //https://coderwall.com/p/nswp1q/calling-other-processes-from-groovy
    //Option 1: Use the absolute path to your script
    static void example1() {
        def scriptPath = "C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool/utc.py"
        def command = "python $scriptPath"
        println command.execute().text
    }

    //Option 2: Further Control of the Context
    static void example2() {
        def parent_dir = new File("C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool")
        def command = "python utc.py"
        def env = [] // in this case, environment is empty
        println command.execute(env, parent_dir).text
    }

    //Consuming Process Output
    static void example3() {
        def parent_dir = new File("C:/Users/i340951/PycharmProjects/mypython/sap.caas/tool")
        def command = "python utc.py"
        def env = System.getenv().collect { k, v -> "$k=$v" }

        Process process = command.execute(env, parent_dir)
        def out = new StringBuffer()
        def err = new StringBuffer()
        process.consumeProcessOutput( out, err )
        process.waitFor()
        if( out.size() > 0 ) println out
        if( err.size() > 0 ) println err
    }

    // cannot run groovy in groovy
    static void example4() {
        def parent_dir = new File("C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool")
        def command = "groovy lab.groovy"
        def env = System.getenv().collect { k, v -> "$k=$v" }

        println env
        env.each {println it}
        println command.execute(env, parent_dir).text
    }

    public static void testGroovy3() throws CompilationFailedException, IOException {
        // 调用带参数的groovy shell时，使用bind绑定数据
        Binding binding = new Binding();
        binding.setProperty("name", "Juxinli");

        GroovyShell groovyShell = new GroovyShell(binding);
        //evaluate(new File('script.groovy'))
        Object result = groovyShell.evaluate(new File("C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool\\lab.groovy"));
        println result.toString()
    }

    static void example5() {
//        testGroovy3()
        Binding binding = new Binding()
        binding.setProperty('name', 'michael')
//        binding.setProperty("name", "Juxinli")

        def result = new GroovyShell(binding).evaluate(new File("C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool\\lab.groovy"))
        println result
    }

    static void example6() {
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("println 'My First Groovy shell.'")
    }

    static void example7() {
        // GroovyScriptEngine的根路径，如果参数是字符串数组，说明有多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool");
        //GroovyScriptEngine engine = new GroovyScriptEngine(new String[] {"src/main/java/com/juxinli/groovy/shell/"});

        Binding binding = new Binding();
        binding.setVariable("name", "juxinli");

        Object result1 = engine.run("lab.groovy", binding);
        System.out.println(result1);
    }

    static void example8() {}

    static void lab2() {
        example5()
    }

    static void _main() {
//        lab1()
//        lab2()
//        lab()
//        lab_a()
        lab_b()
    }

    static void main(String... args) {
        _main()
    }


    @Override
    public String toString() {
        return "MyCli{" +
                "_parent_dir=" + _parent_dir +
                ", env=" + env +
                ", cli=" + cli +
                '}';
    }
}
