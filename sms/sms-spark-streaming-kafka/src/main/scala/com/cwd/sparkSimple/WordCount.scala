package com.cwd.sparkSimple

/**
  * Created by chenweida on 2017/3/15.
  */
import org.apache.spark._

object WordCount {
  def main(args: Array[String]) {

    val appName="myWordCount";
    val hsfs="hdfs://192.168.116.240:9000/spark/test1.log";

    val sparkConf = new SparkConf()
    sparkConf.setAppName(appName)

    val sparkContent=new SparkContext(sparkConf)
    println("输出 start")
    //从hadoop读取文件
    val text= sparkContent.textFile(hsfs).cache()
    text.foreach( printf(_))

    text.map(_.split(" ")).foreach( n=>{println(n(7))})
    println("输出 end")
    sparkContent.stop()
  }
}
