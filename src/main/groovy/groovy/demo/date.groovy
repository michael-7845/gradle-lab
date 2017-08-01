package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
import static java.util.Calendar.getInstance as now
//import org.codehaus.groovy.runtime.TimeCategory
import java.text.SimpleDateFormat

def date = new Date()+1
println date

input = '1998-06-03'
df1 = new SimpleDateFormat("yyyy-MM-dd")
date = df1.parse(input)
df2 = new SimpleDateFormat("MMM/dd/yyyy")
println 'Date was '+df2.format(date)

println now().time

println  df1.format(now().time)

//进行时间的计算
//use(TimeCategory){
//    date3 =  new Date()+1.year+3.hours-2.days
//    println df1.format(date3)
//}

/*
Tue Apr 10 14:59:33 CST 2012
Date was 六月/03/1998
Mon Apr 09 14:59:33 CST 2012
2012-04-09
2013-04-07
*/
