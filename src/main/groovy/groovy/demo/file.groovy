package groovy.demo

/**
 * Created by I340951 on 8/1/2017.
 * http://renjie120.iteye.com/blog/1504827
 */
myFileDir = "d:\\"
myFileName = 'mytest.txt'
myFile = new File(myFileDir + myFileName)

printFileLine = { println "File line:"+it}

myFile.eachLine ( printFileLine )

myFile.write('123')
myFile.append('123')

//读取属性文件：
Properties properties = new Properties()
properties.load(new FileInputStream("d:\\conf.properties"))

println properties.getProperty("filename")
