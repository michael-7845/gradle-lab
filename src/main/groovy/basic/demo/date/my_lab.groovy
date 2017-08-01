package basic.demo.date

import java.text.SimpleDateFormat

/**
 * Created by I340951 on 8/1/2017.
 */
def s ='2017-08-01T02:02:44.371Z'
def d = Date.parse("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", s)
println d

def c1 = d.toCalendar()
c1.add(Calendar.SECOND, 1)
def d1 = c1.getTime()
def c2 = d.toCalendar()
c2.add(Calendar.SECOND, -1)
def d2 = c2.getTime()

def sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
def s1 = sdf.format(d1);
println s1
def s2 = sdf.format(d2);
println s2
