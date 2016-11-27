package com.yihu.cwd.scalaDemo

import java.io.FileNotFoundException


/**
  * Created by Administrator on 2016/11/25.
  */
object MatchDemo {
  def main(args: Array[String]) {
    //println(MatchDemo.matchString("super"))
    //MatchDemo.matchArray(Array("hadoop1", "hadoop5", "hadoop3"));
    //MatchDemo.matchObject(new Student)

  }

  def matchString(data: String): Any = {
    data match {
      case "spark" =>
        println("spark")
        println("spark")
        var a = 1 + 2
        a
      case "hadoop" => println("hadoop");
      case "yarm" => println("spark");
      case "hdfs" => println("hdfs");
      case data_ if data_ == "super" => println("super")
      case _ if data.equals("spark1") => println("spark1");
      case _ => println("no match");
    }
  }

  /**
    * 匹配数组
    *
    * @param array
    * @return
    */
  def matchArray(array: Array[String]): Any = {
    array match {
      case Array("spark") => println("spark")
      case Array(hadoop1, hadoop2, hadoop3) => println(hadoop1 + ":" + hadoop2 + ":" + hadoop3);
      case Array("yarm", _*) => println("yarm");
      case Array("hdfs") => println("hdfs");
      case _ => println("no match");
    }
  }


//  def matchObject(person: Person): Any = {
//    person match {
//      case Student() => println("Student")
//      case Worker() => println("Worker");
//      case _ => println("no match");
//    }
//  }
}




