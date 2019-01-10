package groovy.dsl.groovydemo

import groovy.xml.*
import java.io.*

/*
 * https://www.cnblogs.com/chenjie0949/p/4755389.html
 */
class Task{
    //默认省略public和分号
    String summary
    String description
    Date dueDate
    Map m

    static void lab1() {
        //默认有set和get方法
        Task task1 = new Task()
        task1.setSummary("this is Task1")
        println task1.getSummary()
        //有形参的方法调用，括号可以省略
        task1.setDescription "this is Task class"
        task1.printDescription ""
        task1.printDescription2 "this is Task class", 2
        //可以直接传map
        Task task3 = new Task()
        task3.setM (['summary':'this is Task3','description':'Task'])
        println task3.getM()
        //map的分号可以省略
        Task task2= new Task('summary':'this is Task2','description':'Task')
        println task2.getSummary()
        //括号也可以省略
        Task task4 = new Task()
        task4.setM 'summary':'this is Task4'
        println task4.getM()
    }

    static void main(args){
        lab1()
    }

    public void printDescription(def str){
        println "the task description is : $description"
    }

    public void printDescription2(def str, def number){
        println "the task description is : $description, number is $number"
    }
}