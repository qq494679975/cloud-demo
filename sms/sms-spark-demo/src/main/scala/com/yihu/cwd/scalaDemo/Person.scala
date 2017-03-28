package com.yihu.cwd.scalaDemo

import org.apache.avro.ipc.specific.Person

/**
  * Created by Administrator on 2016/11/25.
  */
object Person {
  //隐式类
  implicit class JiSuan(x: Int) {
    def add(a: Int): Int = a + 1
  }

  implicit def int2String(x: Int) = x.toString   //隐式视图


  implicit var name1: String = "隐式名称1" //隐式参数

  def main(args: Array[String]) {
//    var p = new Person()
//    p.sayName("你好")
//    p.sayName //没有传参数 但是scala会自动去匹配伴生对象或者该方法内有没有带有implicit的字符串  如果有多个隐式参数会报错
//    p.sayName(10) //scala会自动去匹配伴生对象或者该方法内有没有带有implicit的入参是int的方法 然后调用
//
//    println(2.add(1)) //隐式调用上面的类


    val colors1 = Map(
      1 -> List(1, 2, 3, 4),
      2 -> List(1, 2, 3, 5),
      3 -> List(1, 2, 3, 6))
    val colors4 = colors1.getOrElse(1,List()).toSet
    var l = colors4.size
    println(colors4)
  }
}

 class Person {

  def sayName(implicit name: String) = println(name) //name为隐式参数
}



object Man{
  implicit def sayhi() = println("man,hi")   //隐式类
}



