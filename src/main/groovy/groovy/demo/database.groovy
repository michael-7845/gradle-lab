package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
import groovy.sql.Sql
def testYear = '2011'
//下面的Driver类找不到！修改配置文件就可以了。
def sql = Sql.newInstance("jdbc:mysql://localhost:3306/dwz","root","123456"
        ,"com.mysql.jdbc.Driver")
println '数据库名:'+sql.connection.catalog


sql.execute("delete from testtable where name like ? or name like ? or name like ?",['ls%','lish%','%dataSet%'])

println '插入我的数据之前，数据库：'
sql.eachRow("select * from testtable"){
    println "id = ${it.id}   year = ${it.name}  "+it.name
}
println '--------------------------------------'
wid = 123
wname = 'lishuiqing'
//下面必须使用双引号！
sql.execute("insert into testtable(id,name) values (${wid},${wname})")

sql.execute("update testtable set name =? where name = ?",['lsq','lishuiqing'])

//下面使用预定义参数的查询语句
sql.eachRow("select * from testtable where name like ?",["%a%"]){
    println "id = ${it.id}   year = ${it.name}  "+it.name
}
println '--------------------------------------'

println '下面练习使用dataset'
testtable = sql.dataSet('testtable')

/*def anycity = testtable.findAll{ it.id>1 }
anycity.each{
    println 1
}*/

2.times{
    testtable.add(id:it,name:'使用dataSet添加'+it)
}

println "testtable.getSql():"+testtable.getSql()
println '总行数:'+testtable.rows().size()


num = 0
testtable.each{
    //println it[0]----也可以输出指定位置的元素！！
    num = num+1
    println it.id+",year="+it.name
}
//注意要使用${}输出参数的话，必须使用双引号。。。
println "${num}"

println '--------------------------------------'
println "下面显示当前数据库的运行实时状态"
sql.eachRow("show status "){
    //下面在{}里面定义的变量，在外面也找到，也就是作用域是全部的范围！！
    if(it.variable_name=="Uptime")
        uptime = it[1]
    else if (it.variable_name =='Questions')
        questions = it[1]
}
println "数据库运行时间Uptime for Database:${uptime}"
println "数据库查询的条目Number of Queries:${questions}"
println "每分钟的查询条目Queties per Minutes = "+Integer.valueOf(questions)/Integer.valueOf(uptime)

sql.eachRow("show status like 'Com_%'"){
    if(it.variable_name=="Com_insert")
        insertnum = Integer.valueOf(it[1])
    else if (it.variable_name == "Com_select")
        selectnum = Integer.valueOf(it[1])
    else if (it.variable_name == "Com_update")
        updatenum = Integer.valueOf(it[1])
}
println "查询语句有${selectnum},百分比："+100*(selectnum/Integer.valueOf(uptime))+"%"
println "插入语句有${insertnum},百分比："+100*(insertnum/Integer.valueOf(uptime))+"%"
println "更新语句有${updatenum},百分比："+100*(updatenum/Integer.valueOf(uptime))+"%"

println '--------------------------------------'
println '下面将数据库里面的值保存到xml文件'
bldr = new groovy.xml.MarkupBuilder()
bldr.testtable{
    sql.eachRow('select * from testtable'){
        data(id:it.id,name:it.name)
    }
}
println bldr.text()
