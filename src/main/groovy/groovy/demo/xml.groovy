package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
对xml的解析

def CAR_RECORDS = '''
<records>
<car name='HSV Maloo' make='Holden' year='2006'>
<country>Australia</country>
<record type='speed'>Production Pickup Truck with speed of 271kph</record>
</car>
<car name='P50' make='Peel' year='1962'>
<country>Isle of Man</country>
<record type='size'>Smallest Street-Legal Car at 99cm wide and 59 kg in weight</record>
</car>
<car name='Royale' make='Bugatti' year='1931'>
<country>France</country>
<record type='price'>Most Valuable Car at $15 million</record>
</car>
</records>
'''
//解析xml的第一步，进行分析--注意这里的找到的是根节点<records>，所以后面的car。size（）就有值。
def records = new XmlSlurper().parseText(CAR_RECORDS)
//得到节点的数目
assert 3 == records.car.size()
//注意下面的得到car里面的country的条目，而不是直接进行records.country！！
assert 3 == records.car.country.size()
//depthFirst()：计算全部的节点的数目
assert 10 == records.depthFirst().collect{it}.size()

def firstRecord = records.car[0]
//得到一个节点里面的属性name
assert 'car' == firstRecord.name()
//得到一个节点里面的自定义属性，使用@属性名
assert 'Holden' == firstRecord.@make.toString()
//text():得到节点里面的文本信息！不是属性。
assert 'Australia' == firstRecord.country.text()

//对节点进行判断，找到make属性含有e的条目数量
assert 2 == records.car.findAll {it.@make.toString().contains('e')}.size()
//使用正则表达式进行节点筛选
assert 2 == records.car.findAll{it.@make=~'.*e.*'}.size()

//找到全部的国家符合正则表达式的record节点的make属性！
assert ['Holden','Peel'] == records.car.findAll{ it.country =~ '.*s.*a.*'}
        .@make.collect{it.toString()}

//找到全部的type非空的record节点的type属性！！
assert ['speed','size','price'] == records.depthFirst().grep{it.@type!=''}.'@type'*.toString()

def countryOne = records.car[1].country
//查找父节点的两个方法！parent和使用路径表达式
assert 'Peel' == countryOne.parent().@make.toString()
assert 'Peel' == countryOne.'..'.@make.toString()

//将所有的节点按照指定顺序排序之后，再按顺序输出name属性！注意后面的一个×号!
def names = records.car.list().sort{ it.@year.toInteger()}.'@name'*.toString()
assert ['Royale','P50','HSV Maloo'] == names
