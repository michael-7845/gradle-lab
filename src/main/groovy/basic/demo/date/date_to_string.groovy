package basic.demo.date

import java.text.SimpleDateFormat
today = new Date()
println(today)

sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
date11 = sdf.format(today);
println date11

def calendarNow = today.toCalendar().time
println "Now: ${calendarNow} [${calendarNow.class}]"
def calendarTime = today.toCalendar().time.time
println "Now: ${calendarTime} [${calendarTime.class}]"

def timestampNow = today.toTimestamp()
println "Now: ${timestampNow} [${timestampNow.class}]"
def timestampTime = today.toTimestamp().time
println "Now: ${timestampTime} [${timestampTime.class}]"