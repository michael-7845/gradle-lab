package groovy.tool.script

//String script_path = 'C:\\github\\gradle-lab\\src\\main\\groovy\\groovy\\tool\\script'
//String script_name = 'us_address.groovy'
String resource_path = 'C:\\github\\gradle-lab\\mydir'
List files = ['address.txt', ]
List files2 = ['address.txt', ]

static String parse_addess_file(File f) {
    def name_matcher = ~/^Name: (.*)$/
    def phone_matcher = ~/^Phone: (.*)$/
    def address_matcher = ~/^Address: (.*)$/
    StringBuffer sb = new StringBuffer()
    String _name, _phone, _address
    f.eachLine{ line ->
        def name_m = line =~ name_matcher
        def phone_m = line =~ phone_matcher
        def address_m = line =~ address_matcher
        if(name_m.find()) {
            _name = name_m[0][1]
            sb.append("Name: ${_name}").append('\n')
        } else if(phone_m.find()) {
            _phone = phone_m[0][1]
            sb.append("Phone: ${_phone}").append('\n')
        } else if(address_m.find()) {
            _address = address_m[0][1]
            sb.append("Address: ${_address}").append('\n').append('\n')
        }
    }
    f.withPrintWriter { printWriter ->
        printWriter.println(sb.toString())
    }
    sb.toString()
}

static String parse_addess_file2(File f) {
    def name_matcher = ~/^Name(.*)$/
    def phone_matcher = ~/^Phone Number(.*)$/
    def address_matcher = ~/^Address(.*)Save Address$/
    StringBuffer sb = new StringBuffer()
    String _name, _phone, _address
    f.eachLine{ line ->
        def name_m = line =~ name_matcher
        def phone_m = line =~ phone_matcher
        def address_m = line =~ address_matcher
        if(name_m.find()) {
            _name = name_m[0][1]
            sb.append("Name: ${_name}").append('\n')
        } else if(phone_m.find()) {
            _phone = phone_m[0][1]
            sb.append("Phone: ${_phone}").append('\n')
        } else if(address_m.find()) {
            _address = address_m[0][1]
            sb.append("Address: ${_address}").append('\n').append('\n')
        }
    }
    f.withPrintWriter { printWriter ->
        printWriter.println(sb.toString())
    }
    sb.toString()
}

static List adderss_list(File f) {
    def name_matcher = ~/^Name: (.*)$/
    def phone_matcher = ~/^Phone: (.*)$/
    def address_matcher = ~/^Address: (.*)$/
    def state_matcher = ~/^.*(\S{2}),\s(\d{5})\s*$/
    String _name, _phone, _address, _state
    Integer index = 0
    List addr_list = []
    Map addr = [:]
    f.eachLine{ line ->
        def name_m = line =~ name_matcher
        def phone_m = line =~ phone_matcher
        def address_m = line =~ address_matcher
        def state_m = line =~ state_matcher
        if(name_m.find()) {
            _name = name_m[0][1]
            addr.name = _name
            index++
        } else if(phone_m.find()) {
            _phone = phone_m[0][1]
            addr.phone = _phone
            index++
        } else if(address_m.find()) {
            _address = address_m[0][1]
            addr.address = _address
            index++
        }
        if(state_m.find()) {
            _state = state_m[0][1]
            addr.state = _state
        }
        if(index == 3) {
            addr_list << addr
            addr = [:]
            index = 0
        }
    }
    addr_list
}

//files2.each { f ->
//    println parse_addess_file2(new File(resource_path, f))
//}

//files.each { f ->
//    println parse_addess_file(new File(resource_path, f))
//}

files.each { f ->
    List l = adderss_list(new File(resource_path, f))
    Map state_address = [:]
    l.each { addr ->
        state_address[(addr.state)] = addr.address
    }
    state_address.each { state, addr ->
        println "${state}: ${addr}"
    }
}
