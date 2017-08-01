package basic.demo.date

import java.util.Date

def newDate ='2013-03-29T23:28:18.290'
def someDate ='2013-03-29T23:28:18.029'
someDate = someDate.replace("T", " ")
newDate = newDate.replace("T", " ")
println newDate
println someDate

def MyTs = Date.parse("yyyy-MM-dd HH:mm:ss.SSS", someDate)
def newTs = Date.parse("yyyy-MM-dd HH:mm:ss.SSS", newDate)
println("Myts: ${MyTs}")
println("newTs: ${newTs}")

println MyTs.toCalendar().time.time
println newTs.toCalendar().time.time
