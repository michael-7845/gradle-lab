import java.sql.Timestamp

//http://blog.csdn.net/markinlqx/article/details/8755492
println "========1. "
def someDate ='2013-03-29T23:28:18.290'
someDate = someDate.replace("T", " ")
println someDate
def ts = Timestamp.valueOf(someDate)
println(ts)

def string_timestamp(element) {
    def date = Date.parse('yyyy-MM-dd HH:mm:ss.S', element)
    return new Timestamp(date.time)
}

println("Value = " + string_timestamp("2012-08-01 01:12:56.001"))

println "========2. clear"
println("Groovy GDK Date.clearTime()")
def now = new Date()
println "Now: ${now}"
def timelessNow = now.clearTime()
println "Now sans Time: ${timelessNow}"
println "Mutated Time:  ${now}"

println("Groovy GDK Calendar.clearTime()")
now = Calendar.getInstance()
println "Now: ${now}"
now.clearTime()
println "Now is Timeless: ${now}"

println "========3. format"
println("Groovy GDK Date.format(String)")
now = new Date()
println "Now: ${now}"
def dateString = now.format("yyyy-MMM-dd HH:mm:ss a")
println "Formatted Now: ${dateString}"


println("Groovy GDK Calendar.format(String)")
now = Calendar.getInstance()
println "Now: ${now}"
def calendarString = now.format("yyyy-MMM-dd HH:mm:ss a")
println "Formatted Now: ${calendarString}"

//Date.getDateString(), Date.getTimeString(), and Date.getDateTimeString()
println("Groovy GDK Date.getDateString()")
now = new Date()
println "Now: ${now}"
println "Date Only: ${now.getDateString()}"
println "Now Unchanged: ${now}"

println("Groovy GDK Date.getTimeString()")
now = new Date()
println "Now: ${now}"
println "Time Only: ${now.getTimeString()}"
println "Now Unchanged: ${now}"

println("Groovy GDK Date.getDateTimeString()")
now = new Date()
println "Now: ${now}"
println "Date/Time String: ${now.getDateTimeString()}"
println "Now Unchanged: ${now}"

println "========4. date.parse"
//Date.parse(String, String)
println("Groovy GDK Date.parse(String, String)")
def nowString = "2012-11-26 11:45:23"
println "Now String: ${nowString}"
now = Date.parse("yyyy-MM-dd hh:mm:ss", nowString)
println "Now from String: ${now}"

//Date.parseToStringDate(String)
println("Groovy GDK Date.parseToStringDate(String)")
now = new Date()
println "Now: ${now}"
nowString = now.toString()
def nowAgain = Date.parseToStringDate(nowString)
println "From toString: ${nowAgain}"

println "========5. date.toXxx"
//Date.toCalendar() and Date.toTimestamp()
println("Groovy GDK Date.toCalendar()")
now = new Date()
println "Now: ${now}"
def calendarNow = now.toCalendar()
println "Now: ${calendarNow} [${calendarNow.class}]"


println("Groovy GDK Date.toTimestamp()")
now = new Date()
println "Now: ${now}"
def timestampNow = now.toTimestamp()
println "Now: ${timestampNow} [${timestampNow.class}]"

println "========6. date.updated"
//Date.updated(Map)
println("Groovy GDK Date.updated(Map)")

import static java.util.Calendar.YEAR
import static java.util.Calendar.DATE
import static java.util.Calendar.MONTH
now = new Date()
def nextYear = now[YEAR] + 1
def nextDate = now[DATE] + 1
def prevMonth = now[MONTH] - 1
def oneYearFromNow = now.updated(year: nextYear, date: nextDate, month: prevMonth)
println "Now: ${now}"
println "1 Year from Now: ${oneYearFromNow}"